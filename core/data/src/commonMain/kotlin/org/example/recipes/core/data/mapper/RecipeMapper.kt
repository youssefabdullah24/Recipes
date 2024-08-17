package org.example.recipes.core.data.mapper

import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.model.Tag
import org.example.recipes.core.network.model.RecipeDto

fun RecipeDto.toDomain(): Recipe {
    val component = this.sections.first { it.position == 1 }.components
    return Recipe(
        id = this.id,
        title = this.name,
        description = this.description ?: "",
        duration = if(this.totalTimeMinutes != 0) { "${this.totalTimeMinutes} mins"} else{ this.totalTimeTier?.displayTier} ?: this.tags.firstOrNull { it.id == 8091744 || it.id == 64472 || it.id == 8091748}?.displayName ?: "",
        image = this.thumbnailUrl,
        servings = this.yields,
        type = this.tags.first { it.type == "meal" }.displayName,
        nutrition = Nutrition(
            this.nutrition.calories,
            this.nutrition.carbohydrates,
            this.nutrition.fat,
            this.nutrition.fiber,
            this.nutrition.protein,
            this.nutrition.sugar
        ),
        directions = this.instructions.map {
            Direction(
                id = it.id,
                position = it.position,
                startTime = it.startTime,
                endTime = it.endTime,
                appliance = it.appliance,
                temperature = it.temperature,
                text = it.displayText
            )
        },
        ingredients = component.map {
            Ingredient(
                position = it.position,
                measurement = Measurement(
                    name = it.measurements.first().measuringUnit.name,
                    abbreviation = it.measurements.first().measuringUnit.abbreviation,
                    quantity = it.measurements.first().quantity

                ), name = it.ingredient.name
            )
        },
        videoUrl = this.originalVideoUrl ?: this.videoUrl,
        tags = this.tags.map { Tag(it.id, it.displayName, it.type) },
        updatedAt = this.updatedAt,
        createdAt = this.createdAt,
        ratings = Triple(
            this.userRatings.countPositive,
            this.userRatings.countNegative,
            this.userRatings.score
        )

    )
}