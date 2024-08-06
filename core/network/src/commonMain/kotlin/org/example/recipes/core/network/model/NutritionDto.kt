package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionDto(
    @SerialName("calories")
    val calories: Int = 0,
    @SerialName("carbohydrates")
    val carbohydrates: Int = 0,
    @SerialName("fat")
    val fat: Int = 0,
    @SerialName("fiber")
    val fiber: Int = 0,
    @SerialName("protein")
    val protein: Int = 0,
    @SerialName("sugar")
    val sugar: Int = 0,
    @SerialName("updated_at")
    val updatedAt: String = ""
)