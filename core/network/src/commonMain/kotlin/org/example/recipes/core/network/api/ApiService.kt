package org.example.recipes.core.network.api

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.example.recipes.core.network.BuildKonfig
import org.example.recipes.core.network.IApiService
import org.example.recipes.core.network.model.AutoCompleteResponseDto
import org.example.recipes.core.network.model.RecipeDto
import org.example.recipes.core.network.model.RecipeTipsPagingResponseDto
import org.example.recipes.core.network.model.RecipesPagingResponseDto
import org.example.recipes.core.network.model.TagsResponseDto

//TODO: handle response status codes
class ApiService(
    private val client: HttpClient
) : IApiService {
    override suspend fun getRecipesPage(
        query: String,
        tags: String,
        from: Int,
        size: Int
    ): RecipesPagingResponseDto {
        Logger.d("Recipe params: query:$query , tags:$tags , ")
        return try {
            client.get {
                url(BuildKonfig.baseUrl + "/recipes/list")
                parameter("from", from)
                parameter("size", size)
                parameter("q", query)
                parameter("tags", tags)
            }.body<RecipesPagingResponseDto>()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRecipes(query: String, from: Int, size: Int): Result<RecipesPagingResponseDto> {
        return try {
           val response = client.get {
                url(BuildKonfig.baseUrl + "/recipes/list")
                parameter("q", query)
            }
            if(response.status.value == 200) {
                Result.success(response.body<RecipesPagingResponseDto>())

            }else {
                Result.failure(Exception("Rate limited"))

            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRecipe(recipeId: String): Result<RecipeDto> {
        return try {
            Result.success(client.get {
                url(BuildKonfig.baseUrl + "/recipes/get-more-info")
                parameter("id", recipeId)
            }.body<RecipeDto>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTags(): Result<TagsResponseDto> {
        return try {
            Result.success(client.get {
                url(BuildKonfig.baseUrl + "/tags/list")
            }.body<TagsResponseDto>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSuggestions(prefix: String): Result<AutoCompleteResponseDto> {
        return try {
            Result.success(client.get {
                url(BuildKonfig.baseUrl + "/recipes/auto-complete")
                parameter("prefix", prefix)
            }.body<AutoCompleteResponseDto>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSimilarRecipes(recipeId: String): Result<RecipesPagingResponseDto> {
        return try {
            Result.success(client.get {
                url(BuildKonfig.baseUrl + "/recipes/list-similarities")
                parameter("recipe_id", recipeId)
            }.body<RecipesPagingResponseDto>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRecipeTipsPage(
        recipeId: String,
        tags: String,
        from: Int,
        size: Int
    ): RecipeTipsPagingResponseDto {
        return client.get {
            url(BuildKonfig.baseUrl + "/tips/list")
            parameter("id", recipeId)
            parameter("from", from)
            parameter("size", size)
        }.body<RecipeTipsPagingResponseDto>()
    }

    override suspend fun getRecipeTips(
        recipeId: String,
        from: Int,
        size: Int
    ): Result<RecipeTipsPagingResponseDto> {
        return try {
            Result.success(client.get {
                url(BuildKonfig.baseUrl + "/tips/list")
                parameter("id", recipeId)
                parameter("from", from)
                parameter("size", size)
            }.body<RecipeTipsPagingResponseDto>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}