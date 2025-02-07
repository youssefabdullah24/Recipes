package org.example.recipes.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val displayName: String,
    val type: String,
)