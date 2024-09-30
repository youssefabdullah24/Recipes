package org.example.recipes.core.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.example.recipes.core.network.BuildKonfig
import org.example.recipes.core.network.IApiService
import org.example.recipes.core.network.model.AutoCompleteResponseDto
import org.example.recipes.core.network.model.RecipesResponseDto
import org.example.recipes.core.network.model.TagsResponseDto

class ApiService(
    private val client: HttpClient
) : IApiService {
    override suspend fun getRecipesPage(query: String, from: Int, size: Int): RecipesResponseDto {
        val response = client.get {
            url(BuildKonfig.baseUrl + "/recipes/list")
            parameter("from", from)
            parameter("size", size)
            parameter("q", query)

        }.body<RecipesResponseDto>()
        return response
    }

    override suspend fun getTags(): TagsResponseDto {
        val response = client.get {
            url(BuildKonfig.baseUrl + "/tags/list")
        }.body<TagsResponseDto>()
        return response
    }

    override suspend fun getSuggestions(prefix: String): AutoCompleteResponseDto {
        val response = client.get {
            url(BuildKonfig.baseUrl + "/recipes/auto-complete")
            parameter("prefix", prefix)
        }.body<AutoCompleteResponseDto>()
        return response
    }

}