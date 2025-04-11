package org.example.recipes.feature.profile.di

import org.example.recipes.core.data.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel {
        ProfileViewModel(get(), get(), get())
    }
}