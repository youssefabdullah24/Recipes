package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutoCompleteResponseDto(
    @SerialName("results")
    val autoCompleteList: List<AutoCompleteDto?>? = null
)