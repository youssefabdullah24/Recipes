package org.example.recipes.core.data.mapper

import org.example.recipes.core.db.entity.DirectionEntity
import org.example.recipes.core.network.model.InstructionDto

fun InstructionDto.toEntity(recipeId: Int) = DirectionEntity(
    id = this.id ?: -1,
    recipeId = recipeId,
    position = this.position ?: -1,
    startTime = this.startTime ?: -1,
    endTime = this.endTime ?: -1,
    appliance = this.appliance ?: "NA",
    temperature = this.temperature ?: -1,
    text = this.displayText ?: "NA"
)