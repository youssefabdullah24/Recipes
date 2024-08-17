package org.example.recipes.feature.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.example.recipes.core.model.QuickSearchItem
import org.example.recipes.core.ui.ChipItem
import org.example.recipes.core.ui.QuickSearchItem
import org.example.recipes.core.ui.SearchBar


object ExploreTab : Tab {

    @Composable
    override fun Content() {
        Navigator(ExploreRoute())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Explore",
            icon = rememberVectorPainter(Icons.Default.Search)
        )

}

class ExploreRoute : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        ExploreScreen(
            modifier = Modifier.fillMaxSize(),
            quickSearchItems = listOf(
                QuickSearchItem("aa", "rr"),
                QuickSearchItem("bb", "rr"),
                QuickSearchItem("cc", "rr"),
                QuickSearchItem("dd", "rr")
            ),
            popularTags = listOf(
                "aa",
                "bb",
                "cc",
                "dd",
                "ee",
                "ff",
                "gg",
                "hh",
                "ii",
                "jj",
                "kk",
                "ll",
                "mm",
                "nn",
                "oo",
                "pp",
                "qq",
                "rr",
                "ss",
                "tt",
                "uu",
                "vv",
                "ww",
                "xx",
                "yy",
                "zz"
            ),
            onQuickSearchItemClick = {

            }
        )
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    quickSearchItems: List<QuickSearchItem>,
    popularTags: List<String>,
    onQuickSearchItemClick: (QuickSearchItem) -> Unit
) {
    // TODO: ht4of eh ely mmkn t3rdo fl filter mn tags/list fl api lma yft7 4a4t el filter
    //  mmkn brdo tst3ml el "type" ely fl tags w t2smhom sections
    //  msln section cuisine feh "british" "italian" "mexican" etc..
    Column(modifier = modifier) {
        Text(
            text = "Explore",
            modifier = Modifier.padding(start = 16.dp, top = 32.dp),
            style = MaterialTheme.typography.h4
        )
        SearchBar(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp), {}, {})
        Text(
            text = "Quick search",
            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            style = MaterialTheme.typography.h6
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(quickSearchItems.size) {
                QuickSearchItem(
                    modifier = Modifier.size(200.dp),
                    quickSearchItem = quickSearchItems[it],
                    onClick = onQuickSearchItemClick
                )
            }
        }
        Text(
            text = "Popular Tags",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h6
        )
        // TODO: display defined number of chips, adding to the end +Num
        //  https://developer.android.com/develop/ui/compose/layouts/flow#lazy-flow
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            popularTags.forEach {
                ChipItem(
                    modifier = Modifier.wrapContentSize(),
                    title = it
                )
            }
        }
    }
}