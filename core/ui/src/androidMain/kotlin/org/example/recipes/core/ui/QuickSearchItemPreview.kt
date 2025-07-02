package org.example.recipes.core.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun QuickSearchItemPreview() {
    Surface {
        QuickSearchCard(
            quickSearchTag = org.example.recipes.core.model.QuickSearchTag(
                "Test",
                "Test"
            ), onClick = {})
    }
}
