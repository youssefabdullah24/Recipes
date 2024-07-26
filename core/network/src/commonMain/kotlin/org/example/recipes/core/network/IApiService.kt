package org.example.recipes.core.network

import org.example.recipes.core.network.model.RecipesResponse


interface IApiService {
    suspend fun getHomeRecipes(): RecipesResponse
}

