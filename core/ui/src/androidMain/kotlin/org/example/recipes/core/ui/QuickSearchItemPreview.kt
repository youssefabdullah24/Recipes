package org.example.recipes.core.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun QuickSearchItemPreview() {
    Surface {
        QuickSearchComposable(
            quickSearchTag = org.example.recipes.core.model.QuickSearchTag(
                "Test",
                "Test"
            ), onClick = {})
    }
}
