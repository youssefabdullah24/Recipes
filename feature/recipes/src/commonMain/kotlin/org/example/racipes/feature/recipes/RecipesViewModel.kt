package org.example.racipes.feature.recipes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.Recipe
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

class RecipesViewModel(private val repository: IRecipesRepository) : ViewModel() {
    private var _uiState = MutableStateFlow(RecipesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            runCatching {
                repository.getHomeRecipes()
            }.onSuccess { recipes ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        recipes = recipes,
                        errorMsg = null
                    )
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = e.message
                    )
                }
            }
        }
    }

}

data class RecipesUiState(
    var isLoading: Boolean = true,
    var recipes: List<Recipe> = listOf(),
    var errorMsg: String? = null
)

val viewModelModule = module {
    viewModel { RecipesViewModel(get()) }
}
