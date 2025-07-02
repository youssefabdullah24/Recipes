package org.example.recipes.feature.explore

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.recipes.core.model.QuickSearchTag

@Composable
@Preview(showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ExploreScreenPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        Surface {
            ExploreScreen(
                quickSearchTags = listOf(
                    QuickSearchTag("Test", "Test"),
                    QuickSearchTag("Test", "Test"),
                    QuickSearchTag("Test", "Test"),
                    QuickSearchTag("Test", "Test"),
                    QuickSearchTag("Test", "Test"),
                    QuickSearchTag("Test", "Test")
                ), modifier = Modifier.fillMaxSize(), onSearchBarClick = {}, onQuickSearchItemClick = {})
        }
    }
}

