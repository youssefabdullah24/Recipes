package com.example.recipes.feaure.explore

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.recipes.core.model.QuickSearchItem
import org.example.recipes.feature.explore.ExploreScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun ExploreScreenPreview() {
    ExploreScreen(
        quickSearchItems = listOf(
            QuickSearchItem("Test", "Test"),
            QuickSearchItem("Test", "Test"),
            QuickSearchItem("Test", "Test"),
            QuickSearchItem("Test", "Test"),
            QuickSearchItem("Test", "Test"),
            QuickSearchItem("Test", "Test")
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
        ), onQuickSearchItemClick = {},
        modifier = Modifier.fillMaxSize()
    )
}


