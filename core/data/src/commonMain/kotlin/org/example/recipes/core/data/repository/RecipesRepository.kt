package org.example.recipes.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.data.RecipesPagingSource
import org.example.recipes.core.data.mapper.toDomain
import org.example.recipes.core.model.QuickSearchTag
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.network.IApiService

// TODO: refactor and add UseCases
class RecipesRepository(
    private val apiService: IApiService,
    //private val pagingSource: RecipesPagingSource
) : IRecipesRepository {
    override suspend fun getHomeRecipes(): List<Recipe> {
        return apiService.getRecipesPage().results.map { it.toDomain() }
    }

    override fun getRecipesPage(query: String): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { RecipesPagingSource(apiService, query) }
        ).flow.map { it.map { it.toDomain() } }
    }

    override fun getQuickSearchTags(): List<QuickSearchTag> {
        // return apiService.getTags().results.map { it.toDomain() }
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

    override suspend fun getSuggestions(query: String): List<String> {
        return apiService.getSuggestions(query).autoCompleteList?.map {
            it?.display!!
        } ?: emptyList()
    }
}