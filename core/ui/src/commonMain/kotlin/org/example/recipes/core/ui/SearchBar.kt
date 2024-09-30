package org.example.recipes.core.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.alexzhirkevich.cupertino.CupertinoSearchTextField
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveWidget
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi


@OptIn(
    ExperimentalCupertinoApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAdaptiveApi::class
)
@Composable
fun SearchBarComposable(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    query: String = "",
    onSearchClicked: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    AdaptiveWidget(
        material = {
            SearchBar(
                modifier = modifier,
                query = query,
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                active = isActive,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                onActiveChange = { onSearchClicked() },
                content = content
            )
        },
        cupertino = {
            CupertinoSearchTextField(
                onValueChange = {

                }, value = ""
            )
        }
    )
}
