package org.example.recipes.core.data.mapper

import org.example.recipes.core.db.entity.DirectionEntity
import org.example.recipes.core.db.entity.IngredientEntity
import org.example.recipes.core.db.entity.RecipeEntity
import org.example.recipes.core.db.entity.TagEntity
import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.model.Tag
import org.example.recipes.core.network.model.RecipeDto

fun RecipeDto.toDomain(): Recipe {
    val component = this.sections?.firstOrNull { it.position == 1 }?.components ?: emptyList()
    return Recipe(
        id = this.id,
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
                extraComment = it.extraComment ?: "",
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
        ),
        numServings = this.numServings ?: 0
    )
}

fun RecipeDto.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id,
        title = this.name ?: "",
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
        calories = this.nutrition?.calories ?: -1,
        carbohydrates = this.nutrition?.carbohydrates ?: -1,
        fat = this.nutrition?.fat ?: -1,
        fiber = this.nutrition?.fiber ?: -1,
        protein = this.nutrition?.protein ?: -1,
        sugar = this.nutrition?.sugar ?: -1,
        videoUrl = this.originalVideoUrl ?: this.videoUrl,
        updatedAt = this.updatedAt ?: -1,
        createdAt = this.createdAt ?: -1,
        ratingPositive = this.userRatings?.countPositive ?: -1,
        ratingNegative = this.userRatings?.countNegative ?: -1,
        ratingScore = this.userRatings?.score ?: -1.0,
        isFavorite = false,
        hasCooked = false,
        numServings = this.numServings ?: 0
    )
}

fun RecipeEntity.toDomain(
    directionEntities: List<DirectionEntity> = emptyList(),
    ingredientEntities: List<IngredientEntity> = emptyList(),
    tagEntities: List<TagEntity> = emptyList()
): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        description = this.description,
        duration = this.duration,
        nutrition = Nutrition(
            this.calories,
            this.carbohydrates,
            this.fat,
            this.fiber,
            this.protein,
            this.sugar
        ),
        image = this.image,
        servings = this.servings,
        type = this.type,
        videoUrl = this.videoUrl,
        updatedAt = this.updatedAt,
        createdAt = this.createdAt,
        ratings = Triple(
            this.ratingPositive,
            this.ratingNegative,
            this.ratingScore
        ),
        numServings = this.numServings,
        isFavorite = this.isFavorite,
        hasCooked = this.hasCooked,
        directions = directionEntities.map {
            Direction(
                it.id,
                it.position,
                it.startTime,
                it.endTime,
                it.appliance,
                it.temperature,
                it.text
            )
        },
        ingredients = ingredientEntities.map {
            Ingredient(
                it.position,
                Measurement(
                    it.measurementName,
                    it.measurementAbbreviation,
                    it.measurementQuantity
                ),
                name = it.name,
                extraComment = it.extraComment
            )
        },
        tags = tagEntities.map {
            Tag(
                it.displayName,
                it.name,
                it.rootTagName,
                false
            )
        },
    )
}


