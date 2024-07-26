package org.example.recipes.core.network.api

import com.example.recipes.core.network.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import org.example.recipes.core.network.IApiService
import org.example.recipes.core.network.model.RecipesResponse

class ApiService(
    private val client: HttpClient
) : IApiService {
    override suspend fun getHomeRecipes(): RecipesResponse {
        return client.get {
            url(BuildKonfig.baseUrl + "/recipes/list?from=0&size=20")
            header(BuildKonfig.apiKeyHeader, BuildKonfig.apiKey)
            header(BuildKonfig.apiHostHeader, BuildKonfig.apiHost)
        }.body()
    }

}