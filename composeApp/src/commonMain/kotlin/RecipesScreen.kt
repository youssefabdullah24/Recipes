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
import io.github.alexzhirkevich.cupertino.CupertinoText
import org.example.recipes.core.model.QuickSearchItem
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.ui.RecipeItem
import org.example.recipes.core.ui.TrendingItem
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun RecipesScreen(
    onRecipeClick: (Recipe) -> Unit,
    onQuickSearchItemClick: (QuickSearchItem) -> Unit,
    viewModel: RecipesViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    RecipesScreen(
        uiState = uiState,
        onRecipeClick = onRecipeClick,
        modifier = modifier
    )
}

@Composable
fun RecipesScreen(
    uiState: RecipesUiState,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
) {
    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp)
                )
            }

        }

        uiState.errorMsg != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = uiState.errorMsg!!
                )
            }

        }

        else -> {
            LazyColumn(modifier = modifier) {
                item {
                    CupertinoText(
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
                    CupertinoText(
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
                    CupertinoText(
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
                                modifier = Modifier.size(280.dp,240.dp),
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