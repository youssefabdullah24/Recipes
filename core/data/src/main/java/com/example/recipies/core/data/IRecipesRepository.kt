package com.example.recipies.core.data

import com.example.recipies.core.data.model.Recipe

interface IRecipesRepository {
    suspend fun getHomeRecipes(): List<Recipe>
}