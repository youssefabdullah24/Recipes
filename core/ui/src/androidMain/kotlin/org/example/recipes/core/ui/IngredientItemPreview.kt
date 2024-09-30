package org.example.recipes.core.ui


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement


import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IngredientItemPreview() {
    Surface {
        IngredientItem(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            ingredient = Ingredient(
                position = 5342,
                measurement = Measurement(
                    name = "Sharron McKee",
                    abbreviation = "proin",
                    quantity = "nostrum"
                ), name = "Crystal Dominguez"

            )
        )
    }
}

