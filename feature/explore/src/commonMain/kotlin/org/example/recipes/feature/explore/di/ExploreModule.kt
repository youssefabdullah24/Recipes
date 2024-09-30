package org.example.recipes.feature.explore.di

import org.example.recipes.feature.explore.ExploreViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exploreModule = module {
    viewModel { ExploreViewModel(get()) }
}