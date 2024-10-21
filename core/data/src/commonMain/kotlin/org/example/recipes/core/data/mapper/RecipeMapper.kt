package org.example.recipes.core.data.mapper

import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.network.model.RecipeDto

fun RecipeDto.toDomain(): Recipe {
    val component = this.sections?.firstOrNull { it.position == 1 }?.components ?: emptyList()
    return Recipe(
        id = this.id ?: -1,
        title = this.name ?: "NA",
        description = this.description ?: "",
        duration = if (this.totalTimeMinutes != 0) {
            "${this.totalTimeMinutes} mins"
        } else {
            this.totalTimeTier?.displayTier
        }
            ?: this.tags?.firstOrNull { it.id == 8091744 || it.id == 64472 || it.id == 8091748 }?.displayName
            ?: "",
        image = this.thumbnailUrl ?: "NA",
        servings = this.yields ?: "NA",
        type = this.tags?.firstOrNull { it.type == "meal" }?.displayName ?: "",
        nutrition = Nutrition(
            this.nutrition?.calories ?: -1,
            this.nutrition?.carbohydrates ?: -1,
            this.nutrition?.fat ?: -1,
            this.nutrition?.fiber ?: -1,
            this.nutrition?.protein ?: -1,
            this.nutrition?.sugar ?: -1
        ),
        directions = this.instructions?.map {
            Direction(
                id = it.id ?: -1,
                position = it.position ?: -1,
                startTime = it.startTime ?: -1,
                endTime = it.endTime ?: -1,
                appliance = it.appliance,
                temperature = it.temperature,
                text = it.displayText ?: "NA"
            )
        } ?: emptyList(),
        ingredients = component.map {
            Ingredient(
                position = it.position ?: -1,
                measurement = Measurement(
                    name = if (it.measurements?.isNotEmpty() == true) it.measurements?.firstOrNull()?.measuringUnit?.name
                        ?: "" else "",
                    abbreviation = if (it.measurements?.isNotEmpty() == true) it.measurements?.firstOrNull()?.measuringUnit?.abbreviation
                        ?: "" else "",
                    quantity = if (it.measurements?.isNotEmpty() == true) it.measurements?.firstOrNull()?.quantity
                        ?: "" else ""

                ), name = it.ingredient?.name ?: "NA"
            )
        },
        videoUrl = this.originalVideoUrl ?: this.videoUrl,
        tags = this.tags?.map { it.toDomain() } ?: emptyList(),
        updatedAt = this.updatedAt ?: -1,
        createdAt = this.createdAt ?: -1,
        ratings = Triple(
            this.userRatings?.countPositive ?: -1,
            this.userRatings?.countNegative ?: -1,
            this.userRatings?.score ?: -1.0
        )
    )
}