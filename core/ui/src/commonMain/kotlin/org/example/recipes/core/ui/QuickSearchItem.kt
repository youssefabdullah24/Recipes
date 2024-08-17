package org.example.recipes.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.recipes.core.model.QuickSearchItem


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuickSearchItem(
    quickSearchItem: QuickSearchItem,
    onClick: (QuickSearchItem) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(onClick = { onClick(quickSearchItem) }, modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                modifier = Modifier.size(100.dp).clip(CircleShape),
                model = quickSearchItem.image,
                contentDescription = quickSearchItem.title,
                contentScale = ContentScale.FillBounds
            )
            Text(text = quickSearchItem.title)
        }
    }
}