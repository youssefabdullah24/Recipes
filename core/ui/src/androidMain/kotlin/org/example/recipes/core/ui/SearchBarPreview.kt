package org.example.recipes.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun SearchBarPreview() {
    SearchBarComposable(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)){}
}
