package org.example.recipes.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrandDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("slug")
    val slug: String? = null
)