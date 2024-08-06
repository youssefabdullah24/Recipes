package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasurementDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("quantity")
    val quantity: String = "",
    @SerialName("unit")
    val measuringUnit: MeasuringUnitDto = MeasuringUnitDto()
)