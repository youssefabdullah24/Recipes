package org.example.recipes.core.network

import org.example.recipes.core.network.model.RecipesResponseDto


interface IApiService {
    suspend fun getHomeRecipes(): RecipesResponseDto
}

