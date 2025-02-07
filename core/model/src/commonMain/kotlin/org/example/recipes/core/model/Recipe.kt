package org.example.recipes.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,
    val image: String,
    val servings: String,
    val type: String,
    val nutrition: Nutrition,
    val directions: List<Direction>,
    val ingredients: List<Ingredient>,
    val videoUrl: String?,
    val tags: List<Tag>,
    val updatedAt: Int,
    val createdAt: Int,
    val ratings: Triple<Int, Int, Double>, // (positive, negative, score)
)
