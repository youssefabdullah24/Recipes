package com.example.recipes.feature.cook_recipe.di

import com.example.recipes.feature.cook_recipe.CookRecipeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cookRecipeModule = module {
    viewModel { CookRecipeViewModel() }
}