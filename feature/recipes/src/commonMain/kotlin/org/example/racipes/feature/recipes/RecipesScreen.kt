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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveCircularProgressIndicator
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.ui.RecipeItem
import org.example.recipes.core.ui.TrendingItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecipesRoute(
    recipesViewModel: RecipesViewModel = koinViewModel(),
    favorites: List<Recipe>,
    onRecipeClicked: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit
) {
    val uiState by recipesViewModel.recipesUiState.collectAsState()
    recipesViewModel.submitFavoriteList(favorites)

    RecipesScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onRecipeClicked = onRecipeClicked,
        onAddToFavoritesClicked = onAddToFavoritesClicked
    )
}

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun RecipesScreen(
    uiState: RecipesUiState,
    onRecipeClicked: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            AdaptiveCircularProgressIndicator(
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            )
        }
    }


    if (uiState.error != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = uiState.error
            )
        }

    }

    if (uiState.recipes.isNotEmpty()) {
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
                            onAddToFavoritesClicked = {
                                onAddToFavoritesClicked(recipe.id.toString())
                            },
                        )
                    }
                }
            }
        }
    }
}
