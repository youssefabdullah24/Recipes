package org.example.recipes.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.map
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.model.Tag


sealed class SuggestionsState {
    data object Loading : SuggestionsState()
    data class Success(val suggestions: List<String>) : SuggestionsState()
    data class Error(val error: Throwable) : SuggestionsState()
}

data class TagsState(
    val tags: HashMap<String, List<Tag>> = hashMapOf(),
    val error: String? = null
)

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SearchViewModel(private val repository: IRecipesRepository) : ViewModel() {
    private val _favoriteIds = MutableStateFlow<Set<String>>(emptySet())

    private val _suggestionQueryFlow = MutableStateFlow("")
    val suggestionsQueryFlow: StateFlow<String> = _suggestionQueryFlow.asStateFlow()

    private val _searchQueryFlow = MutableStateFlow(Pair("", emptyList<Tag>()))
    val searchQueryFlow: StateFlow<Pair<String, List<Tag>>> = _searchQueryFlow.asStateFlow()

    private val _isActive = MutableStateFlow(true)
    val isActive = _isActive.asStateFlow()

    private var _tagsState = MutableStateFlow(TagsState())
    val tagsState = _tagsState.asStateFlow()


    val recipes: Flow<PagingData<Recipe>> = searchQueryFlow
        .debounce(2000L)
        .filter { searchQueryFlow.value.first.isNotBlank() }
        .flatMapLatest {
            repository.getRecipesPage(searchQueryFlow.value.first, searchQueryFlow.value.second.run {
                val tagsString = StringBuilder("")
                forEach { tagsString.append("${it.name},") }
                tagsString.toString()
            })
        }
        .cachedIn(viewModelScope)
        .combine(_favoriteIds) { pagingData, favorites ->
            pagingData.map { item -> item.copy(isFavorite = favorites.contains(item.id.toString())) }
        }
        .catch {
            emit(PagingData.empty())
            Logger.e(it.stackTraceToString(), it)
        }

    val suggestions: Flow<SuggestionsState> = suggestionsQueryFlow
        .debounce(2000L)
        .filter { it.isNotBlank() }
        .transformLatest { query ->
            emit(SuggestionsState.Loading)
            repository.getSuggestions(query).onSuccess { suggestions ->
                emit(SuggestionsState.Success(suggestions))
            }.onFailure { throwable ->
                emit(SuggestionsState.Error(throwable))
                Logger.e(throwable.stackTraceToString(), throwable)
            }
        }


    init {
        val hash = hashMapOf<String, List<Tag>>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTags()
                .onSuccess { tags ->
                    tags.forEach { tag ->
                        hash[tag.rootTagName.replace('_', ' ').replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }] =
                            tags.filter { it.rootTagName == tag.rootTagName }
                    }

                    _tagsState.update {
                        it.copy(
                            tags = hash,
                            error = null
                        )
                    }
                }.onFailure { throwable ->
                    _tagsState.update { it.copy(error = throwable.message) }
                    Logger.e(throwable.stackTraceToString(), throwable)
                }
        }
    }

    fun setFavorites(favorites: List<String>) {
        _favoriteIds.value = favorites.toSet()
    }

    fun toggleFavorite(id: String) {
        _favoriteIds.update { if (it.contains(id)) it - id else it + id }
    }

    fun suggestQueries(keyword: String) {
        _suggestionQueryFlow.value = keyword
    }

    fun searchRecipes(newQuery: String) {
        _searchQueryFlow.update { it.copy(first = newQuery, second = emptyList()) }
        _suggestionQueryFlow.value = newQuery
    }

    fun setIsActive(isActive: Boolean) {
        _isActive.value = isActive
    }


    fun filterRecipes() {
        val selectedTags = tagsState.value.tags.values.flatten().filter { it.isSelected }
        _searchQueryFlow.update { it.copy(second = selectedTags) }
    }

    fun removeFilter(tag: Tag) {
        _searchQueryFlow.update {
            it.copy(second = it.second - tag)
        }
        _tagsState.update { tagState ->
            val newTagsMap = tagState.tags.mapValues { entry ->
                entry.value.map { currentTag ->
                    if (currentTag.hashCode() == currentTag.hashCode()) {
                        currentTag.copy(isSelected = false)
                    } else {
                        currentTag
                    }
                }
            }
            tagState.copy(tags = HashMap(newTagsMap))
        }

    }

}

