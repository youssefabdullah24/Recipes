package org.example.recipes.core.data.di

import org.example.recipes.core.data.IAuthRepository
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.data.IProfileRepository
import org.example.recipes.core.data.repository.AuthRepository
import org.example.recipes.core.data.repository.RecipesRepository
import org.example.recipes.core.data.repository.ProfileRepository
import org.koin.dsl.module

val dataModule = module {
    single<IRecipesRepository> { RecipesRepository(get()) }

    single<IAuthRepository> { AuthRepository(get()) }

    single<IProfileRepository> { ProfileRepository() }

    /* factory { (apiService: ApiService, query: String) ->
         RecipesPagingSource(apiService, query)
     }

     factory { params ->
         RecipesPagingSource(
             apiService = params.get(),
             query = params.get()
         )
     }*/
}

