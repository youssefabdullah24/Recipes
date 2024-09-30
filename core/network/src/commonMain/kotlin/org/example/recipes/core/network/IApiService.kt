package org.example.recipes.core.network

import org.example.recipes.core.network.model.AutoCompleteResponseDto
import org.example.recipes.core.network.model.RecipesResponseDto
import org.example.recipes.core.network.model.TagsResponseDto


interface IApiService {
    suspend fun getRecipesPage(
        query: String = "",
        from: Int = 0,
        size: Int = 20
    ): RecipesResponseDto

    suspend fun getTags(): TagsResponseDto
    suspend fun getSuggestions(prefix: String): AutoCompleteResponseDto
}

