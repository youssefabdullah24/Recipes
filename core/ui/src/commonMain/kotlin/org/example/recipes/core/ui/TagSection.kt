package org.example.recipes.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Tag

@Composable
fun TagsList(
    sectionTitle: String,
    tags: List<Tag>,
) {
    Text(sectionTitle)
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach {
            ChipItem(tag = it)
        }
    }
}