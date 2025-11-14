package org.example.recipes.feature.search

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.recipes.core.ui.ChipItem
import org.jetbrains.compose.ui.tooling.preview.Preview
/*

data class FilterSection(val title: String, val filters: List<String>)

@Preview
@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    filterSections: List<FilterSection>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(filterSections) {
            FilterSection(filterSection = it)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    filterSection: FilterSection
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Text(
                text = filterSection.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
        item {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)){
                filterSection.filters.forEach {
                    ChipItem(
                        title = it
                    )
                }
            }
        }

    }
}*/
