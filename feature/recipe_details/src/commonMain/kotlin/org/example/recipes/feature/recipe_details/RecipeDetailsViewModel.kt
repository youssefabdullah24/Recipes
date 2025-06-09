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
import org.example.recipes.core.model.Tip


data class RecipeDetailsUiState(
    val isSimilarRecipesLoading: Boolean = true,
    val isRecipeLoading: Boolean = true,
    val areTipsLoading: Boolean = true,
    val recipe: Recipe? = null,
    val similarRecipes: List<Recipe> = emptyList(),
    val recipeTips: List<Tip> = emptyList(),
    val error: String? = null,
)

class RecipeDetailsViewModel(private val repo: IRecipesRepository) : ViewModel() {
    private var _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getRecipeDetails(recipeId: String) {
        fetchRecipeDetails(recipeId)
        fetchSimilarRecipes(recipeId)
        fetchRecipeTips(recipeId)
    }

    private fun fetchRecipeDetails(recipeId: String) {
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
    }

    private fun fetchSimilarRecipes(recipeId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSimilarRecipesLoading = true) }
            repo.getSimilarRecipes(recipeId).onSuccess { recipes ->
                _uiState.update {
                    it.copy(
                        isSimilarRecipesLoading = false,
                        similarRecipes = recipes,
                    )
                }
            }.onFailure { throwable ->
                Logger.e(
                    tag = "RecipeDetailsViewModel@fetchSimilarRecipes",
                    throwable = throwable,
                    messageString = throwable.message.toString()
                )
                _uiState.update {
                    it.copy(
                        isSimilarRecipesLoading = false,
                        error = throwable.message
                    )
                }
            }
        }
    }

    private fun fetchRecipeTips(recipeId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(areTipsLoading = true) }
            repo.getRecipeTips(recipeId).onSuccess { tips ->
                _uiState.update {
                    it.copy(
                        areTipsLoading = false,
                        recipeTips = tips,
                    )
                }
            }.onFailure { throwable ->
                Logger.e(
                    tag = "RecipeDetailsViewModel@fetchRecipeTips",
                    throwable = throwable,
                    messageString = throwable.message.toString()
                )
                _uiState.update {
                    it.copy(
                        isSimilarRecipesLoading = false,
                        error = throwable.message
                    )
                }
            }

        }
    }
}