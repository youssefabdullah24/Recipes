package org.example.recipes

import AppTheme
import Ingredient
import Instruction
import Recipe
import RecipeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
@Preview(showBackground = true)
fun RecipeScreenPreview() {
    AppTheme {
        RecipeScreen(
            recipe = Recipe(
                title = "ubique",
                duration = "atomorum",
                image = "malorum",
                servings = "omnesque",
                type = "civibus",
                instructions = listOf(
                    Instruction(
                        step = 9625,
                        displayText = "convallis",
                        startTime = 4250,
                        endTime = 7643,
                        appliance = "penatibus",
                        temperature = 5939
                    ), Instruction(
                        step = 9625,
                        displayText = "convallis",
                        startTime = 4250,
                        endTime = 7643,
                        appliance = "penatibus",
                        temperature = 5939
                    ), Instruction(
                        step = 9625,
                        displayText = "convallis",
                        startTime = 4250,
                        endTime = 7643,
                        appliance = "penatibus",
                        temperature = 5939
                    ), Instruction(
                        step = 9625,
                        displayText = "convallis",
                        startTime = 4250,
                        endTime = 7643,
                        appliance = "penatibus",
                        temperature = 5939
                    )
                ),
                ingredients = listOf(
                    Ingredient(
                        title = "mi",
                        image = "vulputate",
                        quantity = "ubique"
                    ), Ingredient(
                        title = "mi",
                        image = "vulputate",
                        quantity = "ubique"
                    ), Ingredient(
                        title = "mi",
                        image = "vulputate",
                        quantity = "ubique"
                    ), Ingredient(
                        title = "mi",
                        image = "vulputate",
                        quantity = "ubique"
                    )
                )
            ), modifier = Modifier.fillMaxSize()
        )
    }

}