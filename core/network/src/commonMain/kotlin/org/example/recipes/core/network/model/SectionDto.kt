package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    @SerialName("components")
    val components: List<ComponentDto> = listOf(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("position")
    val position: Int = 0
)