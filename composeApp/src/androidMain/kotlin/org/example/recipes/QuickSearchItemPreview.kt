package org.example.recipes

import AppTheme
import QuickSearchItem
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuickSearchItemPreview() {
    AppTheme {
        Surface {
            QuickSearchItem(modifier = Modifier.size(200.dp, 160.dp),
                QuickSearchItem("test","test"))
        }
    }
}