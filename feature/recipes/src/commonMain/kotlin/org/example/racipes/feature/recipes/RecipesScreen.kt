package org.example.racipes.feature.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.ui.RecipeItem
import org.example.recipes.core.ui.TrendingItem
import org.koin.compose.viewmodel.koinViewModel


object RecipesTab : Tab {

    @Composable
    override fun Content() {
        Navigator(RecipesScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            0u,
            "Home",
            rememberVectorPainter(Icons.Default.Home)
        )

}

class RecipesScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinViewModel<RecipesViewModel>()
        val uiState by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        RecipesScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            onRecipeClick = {
                navigator.push(ScreenRegistry.get(Routes.RecipeDetailsScreenRoute(it)))
            }
        )
    }

}

@Composable
fun RecipesScreen(
    uiState: RecipesUiState,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is RecipesUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp)
                )
            }

        }

        is RecipesUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = uiState.message
                )
            }

        }

        is RecipesUiState.Success -> {
            LazyColumn(modifier = modifier) {
                item {
                    Text(
                        text = "Recipes",
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 32.dp
                        ),
                        style = MaterialTheme.typography.h4
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.recipes) {
                            RecipeItem(
                                modifier = Modifier.size(280.dp, 240.dp),
                                recipe = it,
                                onClick = onRecipeClick
                            )
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    Text(
                        text = "Trending now",
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp
                        ),
                        style = MaterialTheme.typography.h6
                    )
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(uiState.recipes) {
                            TrendingItem(
                                modifier = Modifier
                                    .size(
                                        180.dp,
                                        280.dp
                                    ),
                                recipe = it,
                                onClick = onRecipeClick
                            )
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    Text(
                        text = "Top video recipes",
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp
                        ),
                        style = MaterialTheme.typography.h6
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.recipes) {
                            RecipeItem(
                                modifier = Modifier.size(280.dp, 240.dp),
                                recipe = it,
                                onClick = onRecipeClick
                            )
                        }
                    }
                }
            }
        }
    }
}