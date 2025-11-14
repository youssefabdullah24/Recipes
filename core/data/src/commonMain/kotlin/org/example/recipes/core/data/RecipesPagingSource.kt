package org.example.recipes.core.data

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadResultPage
import org.example.recipes.core.network.model.PagingResponse
import kotlin.reflect.KSuspendFunction4

class RecipesPagingSource<T : Any>(
    private val func: KSuspendFunction4<String, String, Int, Int, PagingResponse<T>>,
    private val query: String,
    private val tags: String,
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val from = params.key ?: 0
        val limit = params.loadSize
        return try {
            val response = func(query, tags, from, limit)
            PagingSourceLoadResultPage(
                data = response.results ?: emptyList(),
                prevKey = if (from == 0) null else from - limit,
                nextKey = if (from >= (response.count ?: -1)) null else from + limit
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

}