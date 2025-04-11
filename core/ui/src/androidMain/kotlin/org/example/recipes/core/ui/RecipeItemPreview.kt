package org.example.recipes.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe

@Preview
@Composable
fun PreviewRecipeItem() {
    Surface {
        RecipeItem(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f)
                .height(280.dp),
            recipe = Recipe(
                title = "ubique",
                duration = "atomorum",
                image = "malorum",
                servings = "omnesque",
                type = "civibus",
                id = 6266,
                description = "suas",
                nutrition = Nutrition(
                    calories = 7249,
                    carbohydrates = 8875,
                    fat = 7873,
                    fiber = 2016,
                    protein = 6857,
                    sugar = 5597
                ),
                directions = listOf(),
                ingredients = listOf(),
                videoUrl = null,
                tags = listOf(),
                updatedAt = 2981,
                createdAt = 9103,
                ratings = Triple(1, 2, 3.0)
            ), onClick = {}){}
    }
}
