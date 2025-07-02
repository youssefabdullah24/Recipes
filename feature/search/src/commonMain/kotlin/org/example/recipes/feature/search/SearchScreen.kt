package org.example.recipes.feature.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems
import com.slapps.cupertino.CupertinoSearchTextField
import com.slapps.cupertino.ExperimentalCupertinoApi
import com.slapps.cupertino.adaptive.AdaptiveCircularProgressIndicator
import com.slapps.cupertino.adaptive.AdaptiveWidget
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import kotlinx.coroutines.launch
import org.example.recipes.core.ui.RecipeItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRoute(
    quickSearchQuery: String?,
    favorites: List<String>,
    searchViewModel: SearchViewModel = koinViewModel(),
    onRecipeClick: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    searchViewModel.setFavorites(favorites)
    SearchScreen(
        searchViewModel,
        quickSearchQuery,
        onRecipeClick,
        onBackPressed,
    ) {
        searchViewModel.toggleFavorite(it)
        onAddToFavoritesClicked(it)
    }
}


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAdaptiveApi::class,
    ExperimentalCupertinoApi::class,
)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    quickSearchQuery: String?,
    onRecipeClick: (Int) -> Unit,
    onBackPressed: () -> Unit,
    onAddToFavoritesClicked: (String) -> Unit,
) {
    LaunchedEffect(quickSearchQuery) {
        if (quickSearchQuery != null) {
            viewModel.setIsActive(false)
            viewModel.searchRecipes(quickSearchQuery)
        }
    }
    val scope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showFilterModal by rememberSaveable { mutableStateOf(false) }
    val suggestionQuery by viewModel.suggestionsQueryFlow.collectAsState()
    val searchQuery by viewModel.searchQueryFlow.collectAsState()
    val suggestions by viewModel.suggestions.collectAsState(SuggestionsState.Loading)
    val isActive by viewModel.isActive.collectAsState()
    val recipes = viewModel.recipes.collectAsLazyPagingItems()
    val padding by animateDpAsState(if (isActive) 8.dp else 16.dp)

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                                    AnimatedVisibility(visible = isActive && suggestionQuery.isNotBlank()) {
                                        IconButton(onClick = { viewModel.suggestQueries("") }) {
                                            Icon(
                                                imageVector = Icons.Default.Clear,
                                                contentDescription = "Clear search query"
                                            )
                                        }
                                    }
                                },
                                leadingIcon = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        IconButton(onClick = {
                                            if (isActive) {
                                                viewModel.setIsActive(false)
                                            } else {
                                                onBackPressed()
                                            }
                                        }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Cancel"
                                            )
                                        }
                                          Icon(
                                              imageVector = Icons.Default.Search,
                                              contentDescription = "Search"
                                          )
                                        //TODO: ??
                                        /*    AnimatedVisibility(visible = isActive) {
                                                Spacer(Modifier.width(8.dp))
                                            }*/
                                    }
                                },
                            )
                        },
                        expanded = isActive,
                        onExpandedChange = { viewModel.setIsActive(true) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(padding),
                    ) {
                        when (suggestions) {
                            SuggestionsState.Loading -> {
                                if (suggestionQuery.isNotBlank()) {
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        AdaptiveCircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
                        value = searchQuery,
                        leadingIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {

                                    onBackPressed()
                                    /*if (isActive) {
                                        viewModel.setIsActive(false)
                                    } else {
                                        onBackPressed()
                                    }*/
                                }) {
                                     Icon(
                                         imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                         contentDescription = "Cancel"
                                     )
                                }
                            }
                        }
                    )
                }
            )
            AnimatedVisibility(
                modifier = Modifier.padding(end = 8.dp),
                visible = searchQuery.isNotBlank()
                        && !isActive
                        && recipes.itemCount > 0
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(onClick = {
                    showFilterModal = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Filter"
                    )
                    Text(text = "Filter")
                }
            }

        }
        when (recipes.loadState.refresh) {
            is LoadState.Loading -> {
                if (suggestionQuery.isNotBlank()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AdaptiveCircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(recipes.itemCount) {
                        RecipeItem(
                            recipe = recipes[it]!!,
                            onClick = { onRecipeClick(it.id) },
                            onAddToFavoritesClicked = {
                                onAddToFavoritesClicked(it)
                            }
                        )
                    }

                    when (recipes.loadState.append) {
                        is LoadState.Loading -> {
                            item {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    AdaptiveCircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                }
                            }
                        }

                        is LoadState.Error -> {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth().height(48.dp)
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

    if (showFilterModal) {
        ModalBottomSheet(
            sheetState = modalSheetState,
            onDismissRequest = {
                scope.launch {
                    modalSheetState.hide()
                }.invokeOnCompletion {
                    if (!modalSheetState.isVisible)
                        showFilterModal = false
                }
            }
        ) {
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    scope.launch {
                        modalSheetState.hide()
                    }.invokeOnCompletion {
                        if (!modalSheetState.isVisible) {
                            showFilterModal = false
                        }
                    }
                }) {
                Text(text = "Filter")
            }
        }
    }
}