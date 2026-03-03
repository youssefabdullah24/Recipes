package org.example.recipes.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagEntity(
    @PrimaryKey
    val displayName: String,
    val name: String,
    val rootTagName: String,
)