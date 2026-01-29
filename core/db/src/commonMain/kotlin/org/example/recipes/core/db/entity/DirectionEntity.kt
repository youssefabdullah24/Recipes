package org.example.recipes.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DirectionEntity(
    @PrimaryKey
    val id: Int,
    val recipeId: Int,
    val position: Int,
    val startTime: Int,
    val endTime: Int,
    val appliance: String = "",
    val temperature: Int = -1,
    val text: String,
)
