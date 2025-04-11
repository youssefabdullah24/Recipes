package org.example.recipes.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import co.touchlab.kermit.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.Recipe


sealed class SuggestionsState{
    data object Loading : SuggestionsState()
    data class Success(val suggestions: List<String>) : SuggestionsState()
    data class Error(val error: Throwable) : SuggestionsState()
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SearchViewModel(private val repository: IRecipesRepository) : ViewModel() {

    private val _suggestionQueryFlow = MutableStateFlow("")
    val suggestionsQueryFlow: StateFlow<String> = _suggestionQueryFlow.asStateFlow()

    private val _searchQueryFlow = MutableStateFlow("")
    val searchQueryFlow: StateFlow<String> = _searchQueryFlow.asStateFlow()

    private val _isActive = MutableStateFlow(true)
    val isActive = _isActive.asStateFlow()

    fun suggestQueries(keyword: String) {
        _suggestionQueryFlow.value = keyword
    }

    fun searchRecipes(newQuery: String) {
        _searchQueryFlow.value = newQuery
        _suggestionQueryFlow.value = newQuery
    }

    val recipes: Flow<PagingData<Recipe>> = searchQueryFlow
        .debounce(2000L)
        .filter { it.isNotBlank() }
        .flatMapLatest {
            repository.getRecipesPage(it)
        }.catch {
            flowOf(PagingData.empty<Recipe>())
            Logger.e(it.stackTraceToString(), it)
        }
        .cachedIn(viewModelScope)

    val suggestions: Flow<SuggestionsState> = suggestionsQueryFlow
        .debounce(2000L)
        .filter { it.isNotBlank() }
        .mapLatest { query ->
            try {
                SuggestionsState.Loading
                val suggestions = repository.getSuggestions(query)
                SuggestionsState.Success(suggestions)
            } catch (throwable: Throwable) {
                SuggestionsState.Error(throwable)
            }
        }



    fun setIsActive(isActive: Boolean) {
        _isActive.value = isActive
    }

}

