package org.example.recipes.core.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuickSearchItemPreview() {
    Surface {
        QuickSearchItem(
            quickSearchItem = org.example.recipes.core.model.QuickSearchItem(
                "Test",
                "Test"
            ), onClick = {})
    }
}
