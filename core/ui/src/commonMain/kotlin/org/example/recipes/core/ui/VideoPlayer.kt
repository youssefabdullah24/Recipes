package org.example.recipes.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(
    url: String,
    seekTo: Double,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
)