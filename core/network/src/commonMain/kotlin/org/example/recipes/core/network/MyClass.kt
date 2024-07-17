package org.example.recipes.core.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

//expect fun getPlatformName() : String

suspend fun testNetwork() : String {
    val response = HttpClient().get("https://ktor.io/docs/")
    return response.bodyAsText()
}