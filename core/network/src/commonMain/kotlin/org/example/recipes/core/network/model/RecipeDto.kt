package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("approved_at")
    val approvedAt: Int = 0,
    @SerialName("aspect_ratio")
    val aspectRatio: String? = "",
    @SerialName("beauty_url")
    val beautyUrl: String? = "",
    @SerialName("brand")
    val brand: String? = "",
    @SerialName("brand_id")
    val brandId: String? = "",
    @SerialName("buzz_id")
    val buzzId: Int? = 0,
    @SerialName("canonical_id")
    val canonicalId: String = "",
    @SerialName("compilations")
    val compilations: List<CompilationDto> = listOf(),
    @SerialName("cook_time_minutes")
    val cookTimeMinutes: Int? = 0,
    @SerialName("country")
    val country: String = "",
    @SerialName("created_at")
    val createdAt: Int = 0,
    @SerialName("credits")
    val credits: List<CreditDto> = listOf(),
    @SerialName("description")
    val description: String? = "",
    @SerialName("draft_status")
    val draftStatus: String = "",
    @SerialName("facebook_posts")
    val facebookPosts: List<String> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("inspired_by_url")
    val inspiredByUrl: String? = "",
    @SerialName("instructions")
    val instructions: List<InstructionDto> = listOf(),
    @SerialName("is_app_only")
    val isAppOnly: Boolean = false,
    @SerialName("is_one_top")
    val isOneTop: Boolean = false,
    @SerialName("is_shoppable")
    val isShoppable: Boolean = false,
    @SerialName("is_subscriber_content")
    val isSubscriberContent: Boolean = false,
    @SerialName("keywords")
    val keywords: String? = "",
    @SerialName("language")
    val language: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("num_servings")
    val numServings: Int = 0,
    @SerialName("nutrition")
    val nutrition: NutritionDto = NutritionDto(),
    @SerialName("nutrition_visibility")
    val nutritionVisibility: String = "",
    @SerialName("original_video_url")
    val originalVideoUrl: String? = "",
    @SerialName("prep_time_minutes")
    val prepTimeMinutes: Int = 0,
    @SerialName("price")
    val price: PriceDto = PriceDto(),
    @SerialName("promotion")
    val promotion: String = "",
    @SerialName("renditions")
    val renditions: List<RenditionDto> = listOf(),
    @SerialName("sections")
    val sections: List<SectionDto> = listOf(),
    @SerialName("seo_path")
    val seoPath: String = "",
    @SerialName("seo_title")
    val seoTitle: String? = "",
    @SerialName("servings_noun_plural")
    val servingsNounPlural: String = "",
    @SerialName("servings_noun_singular")
    val servingsNounSingular: String = "",
    @SerialName("show")
    val show: ShowDto = ShowDto(),
    @SerialName("show_id")
    val showId: Int = 0,
    @SerialName("slug")
    val slug: String = "",
    @SerialName("tags")
    val tags: List<TagDto> = listOf(),
    @SerialName("thumbnail_alt_text")
    val thumbnailAltText: String = "",
    @SerialName("thumbnail_url")
    val thumbnailUrl: String = "",
    @SerialName("tips_and_ratings_enabled")
    val tipsAndRatingsEnabled: Boolean = false,
    @SerialName("topics")
    val topics: List<TopicDto> = listOf(),
    @SerialName("total_time_minutes")
    val totalTimeMinutes: Int? = 0,
    @SerialName("total_time_tier")
    val totalTimeTier: TotalTimeTierDto? = null,
    @SerialName("updated_at")
    val updatedAt: Int = 0,
    @SerialName("user_ratings")
    val userRatings: UserRatingsDto = UserRatingsDto(),
    @SerialName("video_ad_content")
    val videoAdContent: String? = "",
    @SerialName("video_id")
    val videoId: Int? = 0,
    @SerialName("video_url")
    val videoUrl: String? = "",
    @SerialName("yields")
    val yields: String = ""
)