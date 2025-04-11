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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.recipes.core.ui.RecipeItem
import org.example.recipes.core.ui.TrendingItem
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun RecipesRoute(
    recipesViewModel: RecipesViewModel = koinViewModel<RecipesViewModel>(),
    favorites: List<String>,
    onRecipeClicked: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit
) {
    val uiState by recipesViewModel.state.collectAsState()

    RecipesScreen(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 82.dp),
        uiState = uiState,
        favorites = favorites,
        onRecipeClicked = onRecipeClicked,
        onAddToFavoritesClicked = onAddToFavoritesClicked
    )
}

@Composable
fun RecipesScreen(
    uiState: RecipesUiState,
    favorites: List<String>,
    onRecipeClicked: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is RecipesUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.Center)
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
                        items(uiState.recipes) { recipe ->
                            RecipeItem(
                                modifier = Modifier
                                    .size(
                                        280.dp,
                                        240.dp
                                    ),
                                recipe = recipe,
                                isFavorite = favorites.contains(recipe.id.toString()),
                                onClick = { onRecipeClicked(recipe.id) },
                                onAddToFavoritesClicked = { onAddToFavoritesClicked(recipe.id.toString()) }
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
                                onClick = { onRecipeClicked(it.id) }
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
                        items(uiState.recipes) { recipe ->
                            RecipeItem(
                                modifier = Modifier.size(280.dp, 240.dp),
                                recipe = recipe,
                                onClick = { onRecipeClicked(it.id) },
                                onAddToFavoritesClicked = { onAddToFavoritesClicked(recipe.id.toString()) },
                                isFavorite = favorites.contains(recipe.id.toString())

                            )
                        }
                    }
                }
            }
        }
    }
}