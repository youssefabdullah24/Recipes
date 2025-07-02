package org.example.recipes.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slapps.cupertino.CupertinoSearchTextField
import com.slapps.cupertino.ExperimentalCupertinoApi
import com.slapps.cupertino.adaptive.AdaptiveWidget
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAdaptiveApi::class,
    ExperimentalCupertinoApi::class,

    )
@Composable
fun SearchBarComposable(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    AdaptiveWidget(
        material = {
            SearchBar(
                modifier = modifier,
                query = "",
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                active = false,
                onQueryChange = {},
                onSearch = {},
                onActiveChange = { onClick() },
                content = {}
            )
        },
        cupertino = {
            CupertinoSearchTextField(
                modifier = Modifier.clickable { onClick() },
                enabled = false,
                onValueChange = {},
                value = ""
            )
        }
    )
}
