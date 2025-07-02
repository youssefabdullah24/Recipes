package org.example.recipes.feature.recipes

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.racipes.feature.recipes.RecipesScreen
import org.example.racipes.feature.recipes.RecipesUiState
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun RecipesScreenPreview() {
MaterialTheme(colorScheme = darkColorScheme()) {
    Surface{
        RecipesScreen(
            uiState = RecipesUiState(
                recipes = listOf(
                    Recipe(
                        id = 131,
                        title = "Gideon",
                        description = "Randie",
                        duration = "Kayley",
                        image = "Deondre",
                        servings = "Christohper",
                        type = "Keisha",
                        nutrition = Nutrition(
                            calories = 4249,
                            carbohydrates = 1151,
                            fat = 7731,
                            fiber = 3083,
                            protein = 2533,
                            sugar = 9747,
                        ),
                        directions = listOf(),
                        ingredients = listOf(),
                        videoUrl = null,
                        tags = listOf(),
                        updatedAt = 2367,
                        createdAt = 1117,
                        ratings = Triple(1, 2, 3.0),
                        isFavorite = false,
                        hasCooked = false,
                        numServings = 5847

                    )
                )
            ),
            onRecipeClicked = {},
            onAddToFavoritesClicked = { },
            modifier = Modifier.fillMaxSize()
        )
    }
}
}