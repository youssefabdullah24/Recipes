package org.example.recipes.feature.recipe_reviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import co.touchlab.kermit.Logger
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveCircularProgressIndicator
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.example.recipes.core.model.Tip
import org.example.recipes.core.ui.ReviewCard
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RecipeReviewsRoute(
    modifier: Modifier,
    recipeName: String,
    recipeId: String,
) {
    val viewModel: RecipeReviewsViewModel = koinViewModel(parameters = { parametersOf(recipeId) })
    val reviewsUiState = viewModel.reviewsPagingFLow.collectAsLazyPagingItems()
    RecipeReviewsScreen(
        modifier,
        recipeName,
        reviewsUiState
    )
}

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun RecipeReviewsScreen(
    modifier: Modifier,
    recipeName: String,
    tips: LazyPagingItems<Tip>,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        when (tips.loadState.refresh) {
            is LoadState.Loading -> {
                AdaptiveCircularProgressIndicator(modifier = Modifier.size(48.dp).align(Alignment.Center))
            }

            is LoadState.Error -> {
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    onClick = { tips.retry() }) {
                    Text(text = "Failed to load, tap to retry")
                }
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(
                        tips.itemCount,
                        key = { tips[it]?.timestamp!! }) {
                        tips[it]?.let { tip ->
                            Logger.d(tag = "ReviewItem", messageString = tip.toString())
                            ReviewCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                tip = tip
                            ) {

                            }
                        }
                    }

                    when (tips.loadState.append) {
                        is LoadState.Error -> {
                            item {
                                TextButton(
                                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                                    onClick = { tips.retry() }) {
                                    Text(text = "Failed to load, tap to retry")
                                }
                            }
                        }

                        LoadState.Loading -> {
                            item {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    AdaptiveCircularProgressIndicator(modifier = Modifier.padding(8.dp).align(Alignment.Center))
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