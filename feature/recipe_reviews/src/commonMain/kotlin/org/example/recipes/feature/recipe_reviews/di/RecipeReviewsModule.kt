package org.example.recipes.feature.recipe_reviews.di

import org.example.recipes.feature.recipe_reviews.RecipeReviewsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val recipeReviewsModule = module {
    viewModel { (recipeId: String) ->
        RecipeReviewsViewModel(get(), recipeId)
    }
}