package org.example.recipes.feature.search.di

import org.example.recipes.feature.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel {
        SearchViewModel(get())
    }
}