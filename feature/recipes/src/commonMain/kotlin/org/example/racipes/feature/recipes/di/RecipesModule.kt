package org.example.racipes.feature.recipes.di

import org.example.racipes.feature.recipes.RecipesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RecipesViewModel(get()) }
}

