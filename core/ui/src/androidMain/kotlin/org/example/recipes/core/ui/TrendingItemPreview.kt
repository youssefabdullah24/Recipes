package org.example.recipes.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe

@Preview
@Composable
fun TrendingItemPreview() {
    Surface {
        TrendingItem(
            recipe = Recipe(
                id = 6787,
                title = "prompta",
                description = "definitiones",
                duration = "maiestatis",
                image = "felis",
                servings = "cu",
                type = "urna",
                nutrition = Nutrition(
                    calories = 7559,
                    carbohydrates = 6609,
                    fat = 4724,
                    fiber = 2238,
                    protein = 5096,
                    sugar = 8438
                ),
                directions = listOf(
                    Direction(
                        id = 8009,
                        position = 7746,
                        startTime = 1365,
                        endTime = 6603,
                        appliance = null,
                        temperature = null,
                        text = "posse"

                    )
                ),
                ingredients = listOf(
                    Ingredient(
                        position = 8729, measurement = Measurement(
                            name = "Reed Chapman",
                            abbreviation = "fringilla",
                            quantity = "ad"
                        ), name = "Catherine Wilkins",
                        extraComment = ""
                    )
                ),
                videoUrl = null,
                tags = listOf(),
                updatedAt = 6534,
                createdAt = 1387,
                ratings = Triple(1, 2, 3.0),
                numServings = 0
            ), onClick = {}, modifier = Modifier.size(150.dp)

        )

    }
}


