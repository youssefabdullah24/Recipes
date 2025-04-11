package org.example.recipes.feature.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems
import com.slapps.cupertino.CupertinoSearchTextField
import com.slapps.cupertino.ExperimentalCupertinoApi
import com.slapps.cupertino.adaptive.AdaptiveWidget
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import org.example.recipes.core.ui.RecipeItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRoute(
    favorites: List<String>,
    onRecipeClick: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit
) {
    SearchScreen(
        favorites,
        onRecipeClick,
        onAddToFavoritesClicked
    )
}


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAdaptiveApi::class,
    ExperimentalCupertinoApi::class,
)
@Composable
fun SearchScreen(
    favorites: List<String>,
    onRecipeClick: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit
) {
    val viewModel: SearchViewModel = koinViewModel()
    val suggestionQuery by viewModel.suggestionsQueryFlow.collectAsState()
    val searchQuery by viewModel.searchQueryFlow.collectAsState()
    val suggestions by viewModel.suggestions.collectAsState(SuggestionsState.Loading)
    val isActive by viewModel.isActive.collectAsState()
    val recipes = viewModel.recipes.collectAsLazyPagingItems()
    val padding by animateDpAsState(if (isActive) 8.dp else 16.dp)
    Box(modifier = Modifier.fillMaxSize()) {
        AdaptiveWidget(
            material = {
                DockedSearchBar(
                    inputField = {
                        SearchBarDefaults.InputField(
                            modifier = Modifier.fillMaxWidth(),
                            query = suggestionQuery,
                            onQueryChange = viewModel::suggestQueries,
                            onSearch = {
                                viewModel.setIsActive(false)
                                viewModel.searchRecipes(it)
                            },
                            expanded = isActive,
                            onExpandedChange = { viewModel.setIsActive(it) },
                            placeholder = { Text("Search") },
                            trailingIcon = {
                                AnimatedVisibility(visible = isActive) {
                                    IconButton(onClick = { viewModel.suggestQueries("") }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "Clear"
                                        )
                                    }
                                }
                            },
                            leadingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    AnimatedVisibility(visible = isActive) {
                                        IconButton(onClick = { viewModel.setIsActive(false) }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Cancel"
                                            )
                                        }
                                    }
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search"
                                    )
                                    AnimatedVisibility(visible = isActive) {
                                        Spacer(Modifier.width(8.dp))
                                    }
                                }
                            },
                            interactionSource = null,
                        )
                    },
                    expanded = isActive,
                    onExpandedChange = { viewModel.setIsActive(true) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding),
                ) {
                    when (suggestions) {
                        is SuggestionsState.Loading -> {
                            if (suggestionQuery.isNotBlank()) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(
                                            Alignment.Center
                                        )
                                    )
                                }
                            }
                        }

                        is SuggestionsState.Error -> {
                            TextButton(
                                modifier = Modifier,
                                onClick = { recipes.retry() }) {
                                Text(text = "Failed to load, tap to retry")
                            }
                        }

                        is SuggestionsState.Success -> {
                            val suggestionsData =
                                (suggestions as SuggestionsState.Success).suggestions
                            if (suggestionsData.isEmpty()) {
                                Text(text = "No suggestions found")
                            } else {
                                LazyColumn(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                ) {
                                    items(suggestionsData) {
                                        TextButton(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(48.dp),
                                            onClick = {
                                                viewModel.setIsActive(false)
                                                viewModel.searchRecipes(it)

                                            }
                                        ) {
                                            Text(text = it)
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

            },
            cupertino = {
                CupertinoSearchTextField(
                    onValueChange = viewModel::searchRecipes,
                    value = searchQuery
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
        when (recipes.loadState.refresh) {
            is LoadState.Loading -> {
                if (suggestionQuery.isNotBlank()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

            is LoadState.Error -> {
                TextButton(
                    modifier = Modifier,
                    onClick = { recipes.retry() }) {
                    Text(text = "Failed to load, tap to retry")
                }
            }

            else -> {
                // TODO: update when adding to favorites.
                LazyColumn {
                    items(recipes.itemCount) {
                        RecipeItem(
                            recipe = recipes[it]!!,
                            onClick = { onRecipeClick(it.id) },
                            isFavorite = favorites.contains(recipes[it]?.id?.toString()!!),
                            onAddToFavoritesClicked = {
                                onAddToFavoritesClicked(it)
                            }
                        )
                    }

                    recipes.apply {
                        when (loadState.append) {
                            is LoadState.Loading -> {
                                item {
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.align(
                                                Alignment.Center
                                            )
                                        )
                                    }
                                }
                            }

                            is LoadState.Error -> {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(48.dp)
                                    ) {
                                        TextButton(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.Center),
                                            onClick = { recipes.retry() }) {
                                            Text(text = "Failed to load, tap to retry")
                                        }
                                    }
                                }
                            }

                            is LoadState.NotLoading -> {}
                        }
                    }
                }
            }
        }
    }
}