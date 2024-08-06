package org.example.recipes.core.data.repository

import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.data.mapper.toDomain
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.network.IApiService

class RecipesRepository(private val apiService: IApiService) : IRecipesRepository {
    override suspend fun getHomeRecipes(): List<Recipe> {
        return apiService.getHomeRecipes().results.map { it.toDomain() }
    }

}