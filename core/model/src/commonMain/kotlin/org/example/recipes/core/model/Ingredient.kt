package org.example.recipes.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val position: Int,
    val measurement: Measurement,
    val name: String,
)