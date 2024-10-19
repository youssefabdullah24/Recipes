package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstructionDto(
    @SerialName("appliance")
    val appliance: String? = null,
    @SerialName("display_text")
    val displayText: String? = "",
    @SerialName("end_time")
    val endTime: Int? = 0,
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("position")
    val position: Int? = 0,
    @SerialName("start_time")
    val startTime: Int? = 0,
    @SerialName("temperature")
    val temperature: Int? = null,
    @SerialName("hacks")
    val hacks: List<HackDto>? = null
)