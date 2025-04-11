package org.example.recipes.feature.recipe_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.Recipe


data class RecipeDetailsUiState(
    val isSimilarRecipesLoading: Boolean = true,
    val isRecipeLoading: Boolean = true,
    val recipe: Recipe? = null,
    val similarRecipes: List<Recipe> = emptyList(),
    val error: String? = null,
)

class RecipeDetailsViewModel(private val repo: IRecipesRepository) : ViewModel() {
    private var _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getRecipeDetails(recipeId: String) {
        _uiState.update { it.copy(isRecipeLoading = true) }
        viewModelScope.launch {
            val result = repo.getRecipe(recipeId)
            result.onSuccess { recipe ->
                _uiState.update {
                    it.copy(
                        isRecipeLoading = false,
                        recipe = recipe,
                    )
                }
            }.onFailure { throwable ->
                Logger.e(
                    tag = "RecipeDetailsViewModel@getRecipeDetails",
                    throwable = throwable,
                    messageString = throwable.message.toString()
                )
                _uiState.update {
                    it.copy(
                        isRecipeLoading = false,
                        error = throwable.message.toString()
                    )
                }
            }
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isSimilarRecipesLoading = true) }
            try {
                val recipes = repo.getSimilarRecipes(recipeId)
                _uiState.update {
                    it.copy(
                        isSimilarRecipesLoading = false,
                        similarRecipes = recipes,
                    )
                }
            } catch (e: Exception) {
                e.message?.let { message ->
                    _uiState.update {
                        it.copy(
                            isSimilarRecipesLoading = false,
                            error = message
                        )
                    }
                }
            }
        }
    }
}