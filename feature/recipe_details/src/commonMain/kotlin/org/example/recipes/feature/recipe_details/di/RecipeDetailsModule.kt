package org.example.recipes.feature.recipe_details.di

import org.example.recipes.feature.recipe_details.RecipeDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val recipeDetailsModule = module {
    viewModel { RecipeDetailsViewModel(get()) }
}