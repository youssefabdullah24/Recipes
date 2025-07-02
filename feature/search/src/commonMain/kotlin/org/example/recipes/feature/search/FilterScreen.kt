package org.example.recipes.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.recipes.core.ui.ChipItem
import org.jetbrains.compose.ui.tooling.preview.Preview

data class FilterSection(val title: String, val filters: List<String>)

@Preview
@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    filterSections: List<FilterSection>
) {
    LazyColumn(
        modifier = modifier,
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
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = filterSection.title,
            style = MaterialTheme.typography.titleMedium
        )
        // TODO: display defined number of chips, adding to the end +Num
        //  https://developer.android.com/develop/ui/compose/layouts/flow#lazy-flow
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            filterSection.filters.forEach {
                ChipItem(
                    modifier = Modifier.wrapContentSize(),
                    title = it
                )
            }
        }
    }
}