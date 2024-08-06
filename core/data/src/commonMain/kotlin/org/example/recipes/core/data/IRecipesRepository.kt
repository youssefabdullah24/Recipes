package org.example.recipes.core.data

import org.example.recipes.core.model.Recipe


interface IRecipesRepository {
    suspend fun getHomeRecipes(): List<Recipe>
}