package org.example.recipes.feature.recipe_details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe


@Composable
@Preview(showBackground = true)
fun RecipeScreenPreview() {
    RecipeDetailsScreen(
        recipe = Recipe(
            title = "ubique",
            duration = "atomorum",
            image = "malorum",
            servings = "omnesque",
            type = "civibus",
            id = 5120,
            description = "fastidii",
            nutrition = Nutrition(
                calories = 4212,
                carbohydrates = 8940,
                fat = 1193,
                fiber = 4729,
                protein = 3268,
                sugar = 9364
            ),
            directions = listOf(
                Direction(
                    id = 4820,
                    position = 1256,
                    startTime = 5138,
                    endTime = 1210,
                    appliance = null,
                    temperature = null,
                    text = "aenean"
                )
            ),
            ingredients = listOf(
                Ingredient(
                    position = 8439,
                    measurement = Measurement(
                        name = "Mary Randall",
                        abbreviation = "agam",
                        quantity = "velit"
                    ), name = "Ron Roberts"

                )
            ),
            videoUrl = null,
            tags = listOf(),
            updatedAt = 4243,
            createdAt = 2050,
            ratings = Triple(1, 2, 3.0),

            )
    )

}
