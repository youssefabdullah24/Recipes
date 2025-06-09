package org.example.recipes.feature.recipe_reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import org.example.recipes.core.data.IRecipesRepository

class RecipeReviewsViewModel(
    recipesRepository: IRecipesRepository,
    recipeId: String
) : ViewModel() {
    val reviewsPagingFLow = recipesRepository
        .getRecipeTipsPage(recipeId)
        .cachedIn(viewModelScope)
}