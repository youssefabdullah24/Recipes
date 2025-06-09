package org.example.recipes.feature.cook_recipe.di

import org.example.recipes.feature.cook_recipe.CookRecipeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cookRecipeModule = module {
    viewModel { CookRecipeViewModel() }
}