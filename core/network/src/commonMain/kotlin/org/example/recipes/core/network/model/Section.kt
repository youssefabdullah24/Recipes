package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    @SerialName("components")
    val components: List<Component> = listOf(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("position")
    val position: Int = 0
)