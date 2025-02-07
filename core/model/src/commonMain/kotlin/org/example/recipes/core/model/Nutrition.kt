package org.example.recipes.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Nutrition(
    val calories: Int,
    val carbohydrates: Int,
    val fat: Int,
    val fiber: Int,
    val protein: Int,
    val sugar: Int
)