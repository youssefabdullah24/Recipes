package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeTipDto(
    @SerialName("author_avatar_url")
    val authorAvatarUrl: String? = null,
    @SerialName("author_name")
    val authorName: String? = null,
    @SerialName("author_rating")
    val authorRating: Int? = null,
    @SerialName("author_user_id")
    val authorUserId: Int? = null,
    @SerialName("author_username")
    val authorUsername: String? = null,
    @SerialName("author_is_verified")
    val authorIsVerified: Int? = null,
    @SerialName("is_flagged")
    val isFlagged: Boolean? = null,
    @SerialName("recipe_id")
    val recipeId: Int? = null,
    @SerialName("status_id")
    val statusId: Int? = null,
    @SerialName("comment_id")
    val commentId: Int? = null,
    @SerialName("comment_count")
    val commentCount: Int? = null,
    @SerialName("tip_body")
    val tipBody: String? = null,
    @SerialName("tip_id")
    val tipId: Int? = null,
    @SerialName("tip_photo")
    val tipPhoto: TipPhoto? = null,
    @SerialName("created_at")
    val createdAt: Int? = null,
    @SerialName("updated_at")
    val updatedAt: Int? = null,
    @SerialName("upvotes_total")
    val upvotesTotal: Int? = null
)