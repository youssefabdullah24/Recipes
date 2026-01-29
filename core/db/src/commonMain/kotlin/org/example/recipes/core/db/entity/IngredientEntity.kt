package org.example.recipes.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val recipeId: Int,
    val position: Int,
    val name: String,
    val extraComment: String,
    val measurementName: String,
    val measurementAbbreviation: String,
    val measurementQuantity: String
)