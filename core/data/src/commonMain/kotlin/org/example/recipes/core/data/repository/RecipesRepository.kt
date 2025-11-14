package org.example.recipes.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.data.RecipesPagingSource
import org.example.recipes.core.data.mapper.toDomain
import org.example.recipes.core.model.QuickSearchTag
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.model.Tag
import org.example.recipes.core.model.Tip
import org.example.recipes.core.network.IApiService
import org.example.recipes.core.network.model.TagDto
import org.jetbrains.compose.resources.ExperimentalResourceApi
import recipes.core.data.generated.resources.Res

class RecipesRepository(private val apiService: IApiService) : IRecipesRepository {

    override suspend fun getHomeRecipes(): Result<List<Recipe>> {
        apiService.getRecipes().onSuccess {
            return Result.success(it.results.map { it.toDomain() })
        }.onFailure {
            return Result.failure(it)
        }
        return Result.success(emptyList())
    }


    override suspend fun getRecipe(recipeId: String): Result<Recipe> {
        apiService.getRecipe(recipeId).onSuccess {
            return Result.success(it.toDomain())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Throwable())
    }

    override fun getRecipesPage(query: String, tags: String): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { RecipesPagingSource(apiService::getRecipesPage, query, tags) }
        ).flow.map { it.map { it.toDomain() } }
    }

    override fun getRecipeTipsPage(
        recipeId: String,
        from: Int,
        size: Int
    ): Flow<PagingData<Tip>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { RecipesPagingSource(apiService::getRecipeTipsPage, recipeId, "") }
        ).flow.map { it.map { it.toDomain() } }
    }

    override suspend fun getRecipeTips(recipeId: String): Result<List<Tip>> {
        apiService.getRecipeTips(recipeId)
            .onSuccess {
                return Result.success(it.results?.map { it.toDomain() } ?: emptyList())
            }.onFailure {
                return Result.failure(it)
            }
        return Result.success(emptyList())
    }

    override fun getQuickSearchTags(): List<QuickSearchTag> {
        return listOf(
            QuickSearchTag(
                "Breakfast",
                "https://www.themealdb.com/images/media/meals/0206h11699013358.jpg/preview"
            ),
            QuickSearchTag(
                "Lunch",
                "https://www.themealdb.com/images/media/meals/syqypv1486981727.jpg/preview"
            ),
            QuickSearchTag(
                "Dinner",
                "https://www.themealdb.com/images/media/meals/vdwloy1713225718.jpg/preview"
            ),
            QuickSearchTag(
                "Pasta",
                "https://www.themealdb.com/images/media/meals/sutysw1468247559.jpg/preview"
            ),
            QuickSearchTag(
                "Soups",
                "https://www.themealdb.com/images/media/meals/7n8su21699013057.jpg/preview"
            ),
            QuickSearchTag(
                "Seafood",
                "https://www.themealdb.com/images/media/meals/do7zps1614349775.jpg/preview"
            ),
            QuickSearchTag(
                "Comfort Food",
                "https://www.themealdb.com/images/media/meals/k420tj1585565244.jpg/preview"
            ),
            QuickSearchTag(
                "Seasonal",
                "https://www.themealdb.com/images/media/meals/r33cud1576791081.jpg/preview"
            ),
            QuickSearchTag(
                "Appetizers",
                "https://img.buzzfeed.com/video-api-prod/assets/5423b4c783114100b56580b6723f4a4e/BFV21737_ChiliCheeseDogBoats-ThumbA1080.jpg"
            ),
            QuickSearchTag(
                "Asian",
                "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg/preview"
            ),
            QuickSearchTag(
                "Indian",
                "https://www.themealdb.com/images/media/meals/qptpvt1487339892.jpg/preview"
            ),
            QuickSearchTag(
                "Italian",
                "https://www.themealdb.com/images/media/meals/qtqvys1468573168.jpg/preview"
            ),
        )
    }

    override suspend fun getSuggestions(query: String): Result<List<String>> {
        apiService.getSuggestions(query)
            .onSuccess {
                return Result.success(it.autoCompleteList?.mapNotNull { it?.display } ?: emptyList())

            }.onFailure {
                return Result.failure(it)
            }
        return Result.success(emptyList())

    }


    override suspend fun getSimilarRecipes(recipeId: String): Result<List<Recipe>> {
        apiService.getSimilarRecipes(recipeId)
            .onSuccess {
                return Result.success(it.results.map { it.toDomain() })
            }.onFailure {
                return Result.failure(it)
            }
        return Result.success(emptyList())
    }

    @OptIn(ExperimentalResourceApi::class)
    override suspend fun getAllTags(): Result<List<Tag>> {
        return try {
            val file = Res.readBytes("files/tags.json").decodeToString()
            val res = Json.decodeFromString<List<TagDto>>(file).map { it.toDomain() }
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)

        }

    }
}
