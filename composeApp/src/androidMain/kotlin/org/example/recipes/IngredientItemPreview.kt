package org.example.recipes

import AppTheme
import Ingredient
import IngredientItem
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun IngredientItemPreview() {
    AppTheme {
        Surface {
            IngredientItem(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight(),
                ingredient = Ingredient(title = "fabellas", image = "quot", quantity = "atomorum")
            )
        }
    }
}