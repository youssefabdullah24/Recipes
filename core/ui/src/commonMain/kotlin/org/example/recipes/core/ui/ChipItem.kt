package org.example.recipes.core.ui
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.cupertino.CupertinoText

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ChipItem(
    modifier: Modifier = Modifier,
    title: String
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = { isSelected = !isSelected },
        selectedIcon = {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 4.dp),
                imageVector = Icons.Default.Check,
                contentDescription = title
            )
        }) {
        CupertinoText(
            modifier = Modifier
                .wrapContentSize()
                .basicMarquee(Int.MAX_VALUE),
            text = title,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
