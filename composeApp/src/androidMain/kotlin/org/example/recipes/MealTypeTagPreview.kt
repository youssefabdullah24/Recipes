package org.example.recipes

import AppTheme
import ChipItem
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MealTypeTagPreview() {
    AppTheme {
        Surface {
            ChipItem(modifier = Modifier.wrapContentSize(), title = "hi")

        }
    }
}