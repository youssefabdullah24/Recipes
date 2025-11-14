package org.example.recipes.core.ui

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.recipes.core.model.Tag
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MealTypeTagPreview() {
    Surface {
        ChipItem(
            modifier = Modifier.wrapContentSize(),
            tag = Tag("display name", "name", "rootTagName",false)
        )
    }
}
