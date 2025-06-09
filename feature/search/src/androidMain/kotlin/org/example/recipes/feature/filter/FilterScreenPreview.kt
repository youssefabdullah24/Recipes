package org.example.recipes.feature.filter

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FilterScreenPreview() {
    Surface {
        org.example.recipes.feature.search.FilterScreen(
            modifier = Modifier.padding(16.dp),
            filterSections = listOf(
                org.example.recipes.feature.search.FilterSection(
                    "Cuisine",
                    listOf(
                        "ItalianPAPII",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "British"
                    )
                ),
                org.example.recipes.feature.search.FilterSection(
                    "Cuisine",
                    listOf(
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "British"
                    )
                ),
                org.example.recipes.feature.search.FilterSection(
                    "Cuisine",
                    listOf(
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "British"
                    )
                ),
                org.example.recipes.feature.search.FilterSection(
                    "Cuisine",
                    listOf(
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "British"
                    )
                ), org.example.recipes.feature.search.FilterSection(
                    "Cuisine",
                    listOf(
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "Italian",
                        "Mexican",
                        "British"
                    )
                )
            ),
        )
    }
}
