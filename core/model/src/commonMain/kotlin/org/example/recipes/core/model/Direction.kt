package org.example.recipes.core.model

data class Direction(
    val id: Int,
    val position: Int,
    val startTime: Int,
    val endTime: Int,
    val appliance: String?,
    val temperature: Int?,
    val text: String,
)