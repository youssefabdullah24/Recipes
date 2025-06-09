package org.example.recipes.core.model


data class Tip(
    val authorAvatarUrl: String,
    val authorName: String?,
    val authorUsername: String,
    val tipBody: String?,
    val tipPhotoUrl: String?,
    val timestamp: Int,
    val upvotes: Int,
)
