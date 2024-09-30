package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutoCompleteDto(
    @SerialName("display")
    val display: String? = null,
    @SerialName("search_value")
    val searchValue: String? = null,
    @SerialName("type")
    val type: String? = null
)