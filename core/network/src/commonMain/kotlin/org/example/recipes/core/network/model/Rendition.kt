package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rendition(
    @SerialName("aspect")
    val aspect: String = "",
    @SerialName("bit_rate")
    val bitRate: Int? = null,
    @SerialName("container")
    val container: String = "",
    @SerialName("content_type")
    val contentType: String = "",
    @SerialName("duration")
    val duration: Int = 0,
    @SerialName("file_size")
    val fileSize: Int? = null,
    @SerialName("height")
    val height: Int = 0,
    @SerialName("maximum_bit_rate")
    val maximumBitRate: Int? = null,
    @SerialName("minimum_bit_rate")
    val minimumBitRate: Int? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("poster_url")
    val posterUrl: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("width")
    val width: Int = 0
)