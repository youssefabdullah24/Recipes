package org.example.recipes.core.network

import org.example.recipes.core.network.model.AutoCompleteResponseDto
import org.example.recipes.core.network.model.RecipeDto
import org.example.recipes.core.network.model.RecipeTipsPagingResponseDto
import org.example.recipes.core.network.model.RecipesPagingResponseDto
import org.example.recipes.core.network.model.TagsResponseDto


interface IApiService {
    suspend fun getRecipesPage(
        query: String = "",
        tags: String = "",
        from: Int = 0,
        size: Int = 20
    ): RecipesPagingResponseDto

    suspend fun getRecipes(
        query: String = "",
        from: Int = 0,
        size: Int = 20
    ): Result<RecipesPagingResponseDto>

    suspend fun getRecipeTipsPage(
        recipeId: String,
        tags: String = "",
        from: Int = 0,
        size: Int = 20
    ): RecipeTipsPagingResponseDto

    suspend fun getRecipe(recipeId: String): Result<RecipeDto>
    suspend fun getTags(): Result<TagsResponseDto>
    suspend fun getSuggestions(prefix: String): Result<AutoCompleteResponseDto>
    suspend fun getSimilarRecipes(recipeId: String): Result<RecipesPagingResponseDto>
    suspend fun getRecipeTips(
        recipeId: String,
        from: Int = 0,
        size: Int = 20
    ): Result<RecipeTipsPagingResponseDto>
}

