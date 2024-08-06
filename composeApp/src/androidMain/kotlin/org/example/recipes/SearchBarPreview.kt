package org.example.recipes

import AppTheme
import org.example.recipes.core.ui.SearchBar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TrendingItemPreview() {
    AppTheme {
        Surface {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onFilterClicked = {},
                onSearchClicked = {})
        }
    }
}