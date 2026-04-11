package org.example.recipes.core.ui.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppbarState {
    var isVisible by mutableStateOf(false)
    var title by mutableStateOf<String?>(null)
    var showBackground by mutableStateOf(false)
    var onBackPressed by mutableStateOf<() -> Unit>({})
    var actions by mutableStateOf<@Composable RowScope.() -> Unit>({})

}

val LocalAppBarState = compositionLocalOf { AppbarState() }