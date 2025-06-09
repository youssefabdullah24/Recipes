import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.recipes.core.model.QuickSearchTag
import org.example.recipes.feature.explore.ExploreScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun ExploreScreenPreview() {
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


