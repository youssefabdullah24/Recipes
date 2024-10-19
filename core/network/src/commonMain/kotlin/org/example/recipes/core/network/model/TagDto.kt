package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    @SerialName("display_name")
    val displayName: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("name")
    val name: String? = "",
    @SerialName("parent_tag_name")
    val parentTagName: String? = null,
    @SerialName("root_tag_type")
    val rootTagType: String? = "",
    @SerialName("type")
    val type: String? = ""
)