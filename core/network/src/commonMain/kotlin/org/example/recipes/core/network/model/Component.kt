package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    @SerialName("extra_comment")
    val extraComment: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("ingredient")
    val ingredient: Ingredient = Ingredient(),
    @SerialName("measurements")
    val measurements: List<Measurement> = listOf(),
    @SerialName("position")
    val position: Int = 0,
    @SerialName("raw_text")
    val rawText: String = "",
    @SerialName("hacks")
    val hacks: List<Hack>? = listOf()
)