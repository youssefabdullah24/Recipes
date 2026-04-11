package org.example.recipes.core.data.mapper

import org.example.recipes.core.db.entity.IngredientEntity
import org.example.recipes.core.network.model.ComponentDto

fun ComponentDto.toEntity(recipeId: Int) = IngredientEntity(
    id = id ?: -1,
    recipeId = recipeId,
    position = position ?: -1,
    extraComment = extraComment ?: "",
    measurementName = if (measurements?.isNotEmpty() == true) measurements?.firstOrNull()?.measuringUnit?.name
        ?: "" else "",
    measurementAbbreviation = if (measurements?.isNotEmpty() == true) measurements?.firstOrNull()?.measuringUnit?.abbreviation
        ?: "" else "",
    measurementQuantity = if (measurements?.isNotEmpty() == true) measurements?.firstOrNull()?.quantity
        ?: "" else "",
    name = ingredient?.name ?: "NA",
)