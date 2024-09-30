package org.example.recipes.core.network.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.recipes.core.network.BuildKonfig
import org.example.recipes.core.network.IApiService
import org.example.recipes.core.network.api.ApiService
import org.koin.dsl.module


val networkModule = module {
    single<IApiService> {
        ApiService(
            HttpClient {
                install(DefaultRequest) {
                    headers.append(BuildKonfig.apiKeyHeader, BuildKonfig.apiKey)
                    headers.append(BuildKonfig.apiHostHeader, BuildKonfig.apiHost)
                }
                install(HttpTimeout) {
                    this.socketTimeoutMillis = 150000L
                    this.connectTimeoutMillis = 150000L
                    this.requestTimeoutMillis = 150000L
                }
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            println("~~ ktor DEBUG ~~\n$message")
                        }

                    }
                }
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
        )
    }
}
