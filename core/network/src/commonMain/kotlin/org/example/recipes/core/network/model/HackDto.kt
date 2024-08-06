package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HackDto(
    @SerialName("end_index")
    val endIndex: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("match")
    val match: String = "",
    @SerialName("start_index")
    val startIndex: Int = 0
)