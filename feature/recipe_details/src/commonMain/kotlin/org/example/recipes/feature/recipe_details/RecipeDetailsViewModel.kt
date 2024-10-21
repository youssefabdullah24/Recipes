package org.example.recipes.feature.recipe_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.Recipe


data class RecipeDetailsUiState(
    val isSimilarRecipesLoading: Boolean = true,
    val similarRecipes: List<Recipe> = emptyList(),
    val error: String? = null,
)

class RecipeDetailsViewModel(private val repo: IRecipesRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState = _uiState.asStateFlow()


    suspend fun getSimilarRecipes(recipeId: String) {
        _uiState.update { it.copy(isSimilarRecipesLoading = true) }
        runCatching {
            repo.getSimilarRecipes(recipeId)
        }.onSuccess { recipes ->
            _uiState.update {
                it.copy(
                    isSimilarRecipesLoading = false,
                    similarRecipes = recipes,
                    error = null
                )
            }
        }
            .onFailure { throwable ->
                throwable.message?.let { message ->
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