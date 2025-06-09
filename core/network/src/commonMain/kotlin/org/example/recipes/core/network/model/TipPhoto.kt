package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TipPhoto(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("width")
    val width: Int? = null
)