package org.example.racipes.feature.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.Recipe

// todo: add UseCase
class RecipesViewModel(private val recipesRepo: IRecipesRepository) : ViewModel() {
    private var mutableState = MutableStateFlow<RecipesUiState>(RecipesUiState.Loading)
    val state = mutableState.asStateFlow()
    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                recipesRepo.getHomeRecipes()
            }.onSuccess { recipes ->
                mutableState.value = RecipesUiState.Success(recipes)
            }.onFailure { e ->
                Logger.d(e.stackTraceToString())
                mutableState.value = RecipesUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}


sealed class RecipesUiState {
    data object Loading : RecipesUiState()
    data class Success(val recipes: List<Recipe>) : RecipesUiState()
    data class Error(val message: String) : RecipesUiState()
}