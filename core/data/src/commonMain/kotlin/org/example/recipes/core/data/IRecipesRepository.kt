package org.example.recipes.core.data

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.recipes.core.model.QuickSearchTag
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.model.Tip


interface IRecipesRepository {
    suspend fun getHomeRecipes(): Result<List<Recipe>>
    suspend fun getRecipe(recipeId: String): Result<Recipe>
    fun getRecipesPage(query: String = ""): Flow<PagingData<Recipe>>
    fun getQuickSearchTags(): List<QuickSearchTag>
    suspend fun getSuggestions(query: String): Result<List<String>>
    suspend fun getSimilarRecipes(recipeId: String) : Result<List<Recipe>>
    fun getRecipeTipsPage(recipeId: String,
                              from: Int = 0,
                              size: Int = 30) : Flow<PagingData<Tip>>

    suspend fun getRecipeTips(recipeId: String): Result<List<Tip>>
}