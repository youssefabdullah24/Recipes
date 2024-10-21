package org.example.recipes.feature.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.recipes.navigation.Routes
import org.example.recipes.core.model.QuickSearchTag
import org.example.recipes.core.ui.QuickSearchComposable
import org.example.recipes.core.ui.SearchBarComposable
import org.koin.compose.viewmodel.koinViewModel


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
        val screenModel = koinViewModel<ExploreViewModel>()
        val quickSearchTags by screenModel.state.collectAsState()

        ExploreScreen(
            modifier = Modifier.fillMaxSize(),
            quickSearchTags = quickSearchTags,
            onSearchBarClick = {
                navigator.push(ScreenRegistry.get(Routes.SearchScreenRoute))
            },
            onQuickSearchItemClick = {
                // Handle quick search item click
            }
        )
    }
}


@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    quickSearchTags: List<QuickSearchTag>,
    onSearchBarClick: () -> Unit,
    onQuickSearchItemClick: (QuickSearchTag) -> Unit
) {
    // TODO: add cuisine row
    Column(modifier = modifier) {
        Text(
            text = "Explore",
            modifier = Modifier.padding(
                start = 16.dp,
                top = 32.dp
            ),
            style = MaterialTheme.typography.h4
        )


        SearchBarComposable(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){
            onSearchBarClick()
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Quick search",
            modifier = Modifier.padding(
                start = 16.dp,
                top = 8.dp
            ),
            style = MaterialTheme.typography.h6
        )


        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(quickSearchTags.size) {
                QuickSearchComposable(
                    modifier = Modifier.wrapContentSize()
                        .padding(
                            8.dp,
                            12.dp
                        ),
                    quickSearchTag = quickSearchTags[it],
                    onClick = onQuickSearchItemClick
                )
            }
        }

        /*  item {
              Text(
                  text = "Popular Tags",
                  modifier = Modifier.padding(16.dp),
                  style = MaterialTheme.typography.h6
              )
          }
          // TODO: display defined number of chips, adding to the end +Num
          //  https://developer.android.com/develop/ui/compose/layouts/flow#lazy-flow
          item {
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
          }*/
    }
}
