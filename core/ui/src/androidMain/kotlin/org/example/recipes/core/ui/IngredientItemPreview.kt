package org.example.recipes.core.ui


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement


@Preview(
    showBackground = true,
    showSystemUi = true
)
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

