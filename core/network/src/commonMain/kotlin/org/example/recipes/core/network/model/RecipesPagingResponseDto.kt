package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesPagingResponseDto(
    @SerialName("count")
    override val count: Int,
    @SerialName("results")
    override val results: List<RecipeDto>
) : PagingResponse<RecipeDto>