package org.example.recipes

import ExploreScreen
import QuickSearchItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun ExploreScreenPreview() {
    ExploreScreen(
        quickSearchItems = listOf(
            QuickSearchItem(title = "propriae", image = "dictum"),
            QuickSearchItem(title = "propriae", image = "dictum"),
            QuickSearchItem(title = "propriae", image = "dictum"),
            QuickSearchItem(title = "propriaepropriae", image = "dictum"),
            QuickSearchItem(title = "propriaepropriae", image = "dictum"),
            QuickSearchItem(title = "propriaepropriae", image = "dictum"),
        ),
        popularTags = listOf(
            "a",
            "tesdadadadt",
            "tedadadadadadadast",
            "tdada\"dadada\",est",
            "dadada",
            "dadada",
            "dadada",
            "dadada",
            "dad\"dadada\",\"dadada\",\"dadada\",ada"
        ),
        modifier = Modifier.fillMaxSize()
    )
}


