package org.example.recipes.core.ui

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(
    ExperimentalMaterial3Api::class,

)
@Composable
fun SearchBarComposable(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
   // AdaptiveWidget(
      //  material = {
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
      //  },
        /*cupertino = {
            CupertinoSearchTextField(
                modifier = Modifier.clickable { onClick() },
                enabled = false,
                onValueChange = {},
                value = ""
            )
        }*/
   // )
}
