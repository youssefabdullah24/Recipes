package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeTipsPagingResponseDto(
    @SerialName("count")
    override val count: Int? = null,
    @SerialName("results")
    override val results: List<RecipeTipDto>? = null
) : PagingResponse<RecipeTipDto>