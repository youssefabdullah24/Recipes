package org.example.recipes.core.data

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.recipes.core.model.QuickSearchTag
import org.example.recipes.core.model.Recipe


interface IRecipesRepository {
    suspend fun getHomeRecipes(): List<Recipe>
    suspend fun getRecipe(recipeId: String): Recipe
    fun getRecipesPage(query: String = ""): Flow<PagingData<Recipe>>
    fun getQuickSearchTags(): List<QuickSearchTag>
    suspend fun getSuggestions(it: String): List<String>
    suspend fun getSimilarRecipes(recipeId: String) : List<Recipe>
}