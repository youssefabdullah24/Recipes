package org.example.recipes

import AppTheme
import Recipe
import RecipeItem
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true)
@Composable
fun PreviewRecipeItem() {
    AppTheme {
        Surface {
            RecipeItem(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.7f)
                    .height(280.dp),
                recipe = Recipe(
                    title = "usu",
                    duration = "veri",
                    image = "iisque",
                    servings = "homero",
                    type = "ex"

                )
            ) {
            }
        }
    }
}