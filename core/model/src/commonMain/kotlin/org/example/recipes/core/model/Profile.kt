package org.example.recipes.core.model

import kotlinx.serialization.Serializable


@Serializable
data class Profile(
    val name: String,
    val email: String,
    val image: String?,
    val favorites: List<String>,
    val cooked: List<String>,
)
