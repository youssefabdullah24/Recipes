package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String = ""
)