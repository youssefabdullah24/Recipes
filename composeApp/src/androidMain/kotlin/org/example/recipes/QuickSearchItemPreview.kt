package org.example.recipes

import AppTheme
import org.example.recipes.core.ui.QuickSearchItem
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuickSearchItemPreview() {
    AppTheme {
        Surface {
            QuickSearchItem(quickSearchItem = org.example.recipes.core.model.QuickSearchItem("Test", "Test"), onClick = {})
        }
    }
}