package org.example.recipes.core.ui

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Tag

@Composable
fun ChipItem(
    modifier: Modifier = Modifier,
    tag: Tag,
) {
    var isSelected by rememberSaveable { mutableStateOf(tag.isSelected) }
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = {
            tag.isSelected = !tag.isSelected
            isSelected = tag.isSelected
        },
        leadingIcon = {
            if (isSelected) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 4.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = tag.displayName
                )
            }
        }, label = {
            Text(
                modifier = Modifier.basicMarquee(Int.MAX_VALUE),
                text = tag.displayName,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        })
}
