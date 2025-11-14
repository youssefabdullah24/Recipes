package org.example.recipes.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val displayName: String,
    val name: String,
    val rootTagName: String,
    var isSelected: Boolean,
)