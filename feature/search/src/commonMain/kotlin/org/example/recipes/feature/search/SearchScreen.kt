package org.example.recipes.feature.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
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
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.slapps.cupertino.CupertinoSearchTextField
import com.slapps.cupertino.ExperimentalCupertinoApi
import com.slapps.cupertino.adaptive.AdaptiveCircularProgressIndicator
import com.slapps.cupertino.adaptive.AdaptiveWidget
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import kotlinx.coroutines.launch
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.model.Tag
import org.example.recipes.core.ui.RecipeItem
import org.example.recipes.core.ui.TagsList
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

    val suggestionQuery by searchViewModel.suggestionsQueryFlow.collectAsState()
    val searchQuery by searchViewModel.searchQueryFlow.collectAsState()
    val suggestions by searchViewModel.suggestions.collectAsState(SuggestionsState.Loading)
    val isActive by searchViewModel.isActive.collectAsState()
    val recipes = searchViewModel.recipes.collectAsLazyPagingItems()
    val tags by searchViewModel.tagsState.collectAsState()


    LaunchedEffect(quickSearchQuery) {
        if (!quickSearchQuery.isNullOrBlank()) {
            searchViewModel.setIsActive(false)
            searchViewModel.searchRecipes(quickSearchQuery)
        }
    }

    SearchScreen(
        quickSearchQuery = quickSearchQuery,
        onRecipeClick = onRecipeClick,
        onBackPressed = {
            if (isActive) {
                searchViewModel.setIsActive(false)
            } else {
                onBackPressed()
            }
        },
        onAddToFavoritesClicked = { recipeString ->
            searchViewModel.toggleFavorite(recipeString)
            onAddToFavoritesClicked(recipeString)
        },
        onSuggestionQueryChange = { query ->
            searchViewModel.suggestQueries(query)
        },

        onSearch = { query -> searchViewModel.searchRecipes(query) },
        onActiveChange = { active -> searchViewModel.setIsActive(active) },
        onClearSuggestionQuery = { searchViewModel.suggestQueries("") },
        suggestionQuery = suggestionQuery,
        searchQuery = searchQuery,
        suggestions = suggestions,
        isActive = isActive,
        recipes = recipes,
        tagsState = tags,
        onRetryLoadSuggestions = {},
        onRetryLoadRecipes = { recipes.retry() },
        onFilterClicked = { searchViewModel.filterRecipes() },
        onRemoveTagClicked = { searchViewModel.removeFilter(it) }
    )
}

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAdaptiveApi::class,
    ExperimentalCupertinoApi::class,
)
@Composable
fun SearchScreen(
    quickSearchQuery: String?,
    onRecipeClick: (Int) -> Unit,
    onBackPressed: () -> Unit,
    onAddToFavoritesClicked: (String) -> Unit,
    onSuggestionQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    onClearSuggestionQuery: () -> Unit,
    suggestionQuery: String,
    searchQuery: Pair<String, List<Tag>>,
    suggestions: SuggestionsState,
    isActive: Boolean,
    recipes: LazyPagingItems<Recipe>,
    tagsState: TagsState,
    onRetryLoadSuggestions: () -> Unit,
    onRetryLoadRecipes: () -> Unit,
    onFilterClicked: () -> Unit,
    onRemoveTagClicked: (Tag) -> Unit
) {
    LaunchedEffect(quickSearchQuery) {
        if (quickSearchQuery != null) {
            onActiveChange(false)
            onSearch(quickSearchQuery)
        }
    }
    val scope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showFilterModal by rememberSaveable { mutableStateOf(false) }
    val padding by animateDpAsState(if (isActive) 8.dp else 16.dp)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AdaptiveWidget(
                material = {
                    DockedSearchBar(
                        inputField = {
                            SearchBarDefaults.InputField(
                                modifier = Modifier.fillMaxWidth(),
                                query = suggestionQuery,
                                onQueryChange = onSuggestionQueryChange,
                                onSearch = {
                                    onActiveChange(false)
                                    onSearch(it)
                                },
                                expanded = isActive,
                                onExpandedChange = { onActiveChange(it) },
                                placeholder = { Text("Search") },
                                trailingIcon = {
                                    AnimatedVisibility(visible = isActive && suggestionQuery.isNotBlank()) {
                                        IconButton(onClick = onClearSuggestionQuery) {
                                            Icon(
                                                imageVector = Icons.Default.Clear,
                                                contentDescription = "Clear search query"
                                            )
                                        }
                                    }
                                },
                                leadingIcon = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        IconButton(onClick = onBackPressed) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Cancel"
                                            )
                                        }
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search"
                                        )
                                    }
                                },
                            )
                        },
                        expanded = isActive,
                        onExpandedChange = { onActiveChange(true) },
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
                                    onClick = onRetryLoadSuggestions
                                ) {
                                    Text(text = "Failed to load, tap to retry")
                                }
                            }

                            is SuggestionsState.Success -> {
                                val suggestionsData = suggestions.suggestions
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
                                                    onActiveChange(false)
                                                    onSearch(it)

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
                        modifier = Modifier.padding(top = 8.dp),
                        onValueChange = onSearch,
                        value = searchQuery.first,
                        leadingIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    onBackPressed()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Cancel"
                                    )
                                }
                            }
                        }, trailingIcon = {
                            IconButton(onClick = {
                                showFilterModal = true
                            }) {
                                Icon(
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = "Filter"
                                )
                            }
                        }
                    )
                }
            )


        }

        AnimatedVisibility(searchQuery.second.isNotEmpty()) {
            FlowRow(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                searchQuery.second.forEach {
                    InputChip(
                        onClick = { onRemoveTagClicked(it) },
                        selected = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Remove filter"
                            )
                        },
                        label = { Text(text = it.displayName) }
                    )
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
                    onClick = onRetryLoadRecipes
                ) {
                    Text(text = "Failed to load, tap to retry")
                }
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal =8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(recipes.itemCount) {
                        RecipeItem(
                            recipe = recipes[it]!!,
                            onClick = { recipe -> onRecipeClick(recipe.id) },
                            onAddToFavoritesClicked = { recipeId ->
                                onAddToFavoritesClicked(recipeId)
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
                                        onClick = onRetryLoadRecipes
                                    ) {
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
            modifier = Modifier.scrollable(rememberScrollState(), Orientation.Vertical),
            sheetState = modalSheetState,
            onDismissRequest = {
                scope.launch {
                    modalSheetState.hide()
                }.invokeOnCompletion {
                    if (!modalSheetState.isVisible) showFilterModal = false
                }
            }
        ) {
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    scope.launch {
                        modalSheetState.hide()
                    }.invokeOnCompletion {
                        if (!modalSheetState.isVisible) showFilterModal = false
                        onFilterClicked()
                    }

                }) {
                Text(text = "Filter")
            }
            LazyColumn {
                items(tagsState.tags.toList()) {
                    TagsList(sectionTitle = it.first, tags = it.second)
                }
            }
        }

    }
}
