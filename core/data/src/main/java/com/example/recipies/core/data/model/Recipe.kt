package com.example.recipies.core.data.model

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,
    val image: String,
    val servings: String,
    val type: String,
    val nutrition: List<Nutrition>,
    val directions: List<Direction>,
    val ingredients: List<Ingredient>,
    val videoUrl: String?,
    val tags: List<Tag>,
    val updatedAt: Long,
    val createdAt: Long,
    val ratings: Triple<Int, Int, Float>, // (positive, negative, score)
)

data class Measurement(
    val name: String,
    val abbreviation: String,
    val quantity: String
)

data class Ingredient(
    val position: Int,
    val measurement: Measurement,
    val name: String,
)

data class Direction(
    val id: Int,
    val position: Int,
    val startTime: Int,
    val endTime: Int,
    val appliance: String?,
    val temperature: Int?,
    val text: String,
)

data class Tag(
    val id: Int,
    val displayName: String,
    val type: String,
)

data class Nutrition(
    val calories: Int,
    val carbohydrates: Int,
    val fat: Int,
    val fiber: Int,
    val protein: Int,
    val sugar: Int
)