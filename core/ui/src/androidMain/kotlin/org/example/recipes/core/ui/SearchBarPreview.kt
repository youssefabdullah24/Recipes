package org.example.recipes.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBarPreview() {
    SearchBar(onSearchClicked = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)) {

    }
}
