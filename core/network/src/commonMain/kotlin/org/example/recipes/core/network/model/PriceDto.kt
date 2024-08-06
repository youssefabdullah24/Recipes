package org.example.recipes.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    @SerialName("consumption_portion")
    val consumptionPortion: Int = 0,
    @SerialName("consumption_total")
    val consumptionTotal: Int = 0,
    @SerialName("portion")
    val portion: Int = 0,
    @SerialName("total")
    val total: Int = 0,
    @SerialName("updated_at")
    val updatedAt: String = ""
)