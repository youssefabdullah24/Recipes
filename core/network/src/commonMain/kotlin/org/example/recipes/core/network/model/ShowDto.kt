package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowDto(
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("name")
    val name: String? = ""
)