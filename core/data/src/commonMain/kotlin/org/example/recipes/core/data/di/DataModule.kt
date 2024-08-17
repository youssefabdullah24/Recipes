package org.example.recipes.core.data.di

import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.data.repository.RecipesRepository
import org.koin.dsl.module

val dataModule = module {
    single<IRecipesRepository> {
        RecipesRepository(get())
    }
}
