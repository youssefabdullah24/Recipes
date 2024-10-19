package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    @SerialName("created_at")
    val createdAt: Int? = 0,
    @SerialName("display_plural")
    val displayPlural: String? = "",
    @SerialName("display_singular")
    val displaySingular: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("name")
    val name: String? = "",
    @SerialName("updated_at")
    val updatedAt: Int? = 0
)