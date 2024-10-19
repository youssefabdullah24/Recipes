package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompilationDto(
    @SerialName("approved_at")
    val approvedAt: Int? = 0,
    @SerialName("aspect_ratio")
    val aspectRatio: String? = "",
    @SerialName("beauty_url")
    val beautyUrl: String? = "",
    @SerialName("buzz_id")
    val buzzId: Int? = 0,
    @SerialName("canonical_id")
    val canonicalId: String? = "",
    @SerialName("country")
    val country: String? = "",
    @SerialName("created_at")
    val createdAt: Int? = 0,
    @SerialName("description")
    val description: String? = "",
    @SerialName("draft_status")
    val draftStatus: String? = "",
    @SerialName("facebook_posts")
    val facebookPosts: List<String>? = listOf(),
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_shoppable")
    val isShoppable: Boolean? = false,
    @SerialName("keywords")
    val keywords: String? = "",
    @SerialName("language")
    val language: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("promotion")
    val promotion: String? = "",
    @SerialName("show")
    val show: List<ShowDto>? = listOf(),
    @SerialName("slug")
    val slug: String? = "",
    @SerialName("thumbnail_alt_text")
    val thumbnailAltText: String? = "",
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = "",
    @SerialName("video_id")
    val videoId: Int? = 0,
    @SerialName("video_url")
    val videoUrl: String? = null
)