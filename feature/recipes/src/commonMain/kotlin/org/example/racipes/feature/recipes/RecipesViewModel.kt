package org.example.racipes.feature.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.Recipe

data class RecipesUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String? = null
)

// todo: add UseCase
class RecipesViewModel(private val recipesRepo: IRecipesRepository) : ViewModel() {
    private var _recipesUiState = MutableStateFlow(RecipesUiState())
    val recipesUiState = _recipesUiState.asStateFlow()

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            _recipesUiState.update { it.copy(isLoading = true) }
            recipesRepo.getHomeRecipes().onSuccess { recipes ->
                _recipesUiState.update {
                    it.copy(
                        isLoading = false,
                        recipes = recipes,
                        error = null
                    )
                }
            }.onFailure { throwable ->
                Logger.e(
                    tag = "RecipesViewModel@getRecipes",
                    throwable = throwable,
                    messageString = throwable.message.toString()
                )
                _recipesUiState.update {
                    it.copy(
                        isLoading = false,
                        error = throwable.message
                    )
                }
            }
        }
    }

    fun submitFavoriteList(favorites: List<Recipe>) {
        _recipesUiState.update { it.copy(recipes = it.recipes.map { it.copy(isFavorite = favorites.map { it.id }.contains(it.id)) }) }
    }
}
