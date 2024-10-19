package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRatingsDto(
    @SerialName("count_negative")
    val countNegative: Int? = 0,
    @SerialName("count_positive")
    val countPositive: Int? = 0,
    @SerialName("score")
    val score: Double? = 0.0
)