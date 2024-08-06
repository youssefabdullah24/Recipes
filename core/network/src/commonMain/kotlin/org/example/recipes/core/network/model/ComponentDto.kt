package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComponentDto(
    @SerialName("extra_comment")
    val extraComment: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("ingredient")
    val ingredient: IngredientDto = IngredientDto(),
    @SerialName("measurements")
    val measurements: List<MeasurementDto> = listOf(),
    @SerialName("position")
    val position: Int = 0,
    @SerialName("raw_text")
    val rawText: String = "",
    @SerialName("hacks")
    val hacks: List<HackDto>? = listOf()
)