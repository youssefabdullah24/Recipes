package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasuringUnitDto(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("display_plural")
    val displayPlural: String = "",
    @SerialName("display_singular")
    val displaySingular: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("system")
    val system: String = ""
)