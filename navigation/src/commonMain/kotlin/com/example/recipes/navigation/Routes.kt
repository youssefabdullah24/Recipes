package com.example.recipes.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import org.example.recipes.core.model.Recipe

sealed class Routes : ScreenProvider {
    data object RecipesScreenRoute : Routes()
    data object ExploreScreenRoute : Routes()
    data class RecipeDetailsScreenRoute(val recipe: Recipe?) : Routes()
}