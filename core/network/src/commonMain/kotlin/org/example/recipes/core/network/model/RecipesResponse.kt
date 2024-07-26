package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("results")
    val results: List<Result> = listOf()
)