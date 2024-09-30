package org.example.recipes.core.data

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadResultPage
import org.example.recipes.core.network.IApiService
import org.example.recipes.core.network.model.RecipeDto

class RecipesPagingSource(
    private val apiService: IApiService,
    private val query: String
) : PagingSource<Int, RecipeDto>() {

    override fun getRefreshKey(state: PagingState<Int, RecipeDto>): Int? {
        return state.anchorPosition
    }

    // from: 0, 0+size .... until count
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeDto> {
        val from = params.key ?: 0
        val limit = params.loadSize
        return try {
            val response = apiService.getRecipesPage(query, from, limit)
            PagingSourceLoadResultPage(
                data = response.results,
                prevKey = if (from == 0) null else from - limit,
                nextKey = if (from >= response.count) null else from + limit
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

}