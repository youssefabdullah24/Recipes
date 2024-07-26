package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = ""
)