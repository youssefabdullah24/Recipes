package org.example.recipes.core.db.entity

import androidx.room.Relation

data class RecipeWithDirections(
    val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id", // id in RecipeEntity
        entityColumn = "recipeId" // foreign in DirectionEntity
    )
    val directions: List<DirectionEntity>
)
