package org.example.recipes.feature.recipe_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.ui.IngredientItem
import org.example.recipes.core.ui.InstructionItem
import org.example.recipes.core.ui.RecipeItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecipeDetailsRoute(
    recipeId: String,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = koinViewModel<RecipeDetailsViewModel>(),
    onRecipeClick: (Recipe) -> Unit,
    onCookRecipeClick: (Recipe) -> Unit,
    onSaveRecipeClick: (Recipe) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.recipe == null) {
        LaunchedEffect(recipeId) {
            viewModel.getRecipeDetails(recipeId)
            //viewModel.getSimilarRecipes(recipeId)
        }
    }
    RecipeDetailsScreen(
        modifier,
        uiState,
        onRecipeClick = onRecipeClick,
        onSaveRecipeClick = onSaveRecipeClick,
        onCookRecipeClick = onCookRecipeClick,
    )
}

@Composable
internal fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: RecipeDetailsUiState,
    onRecipeClick: (Recipe) -> Unit,
    onSaveRecipeClick: (Recipe) -> Unit,
    onCookRecipeClick: (Recipe) -> Unit,
) {
    val recipe = uiState.recipe
    val imageHeight = 360.dp
    val cardMinHeight = 60.dp
    var scrollOffset by remember { mutableStateOf(0f) }
    val density = LocalDensity.current
    val totalSteps = recipe?.directions?.size
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var hasScrolledUp by remember { mutableStateOf(false) }
    val shouldShowBottomTab by remember { derivedStateOf { scrollOffset < 0f } }

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress &&
            listState.firstVisibleItemIndex == 0 &&
            hasScrolledUp
        ) {
            scope.launch {
                // Delay to allow the scroll to settle before resetting scrollOffset
                delay(100)
                scrollOffset = 0f
                hasScrolledUp = false
            }
        }
    }
    Box(modifier = modifier) {
        if (uiState.isRecipeLoading) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp).align(Alignment.Center))
        } else if (uiState.error != null) {
            Text(text = uiState.error, modifier = Modifier.align(Alignment.Center))
        } else {
            recipe?.let { recipe ->
                AsyncImage(
                    model = recipe.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .height(imageHeight + with(density) { scrollOffset.toDp() })

                )
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(object : NestedScrollConnection {
                            override fun onPreScroll(
                                available: Offset,
                                source: NestedScrollSource
                            ): Offset {
                                val delta = available.y
                                val newOffset = (scrollOffset + delta).coerceIn(
                                    with(density) { -imageHeight.toPx() + cardMinHeight.toPx() },
                                    0f
                                )
                                scrollOffset = newOffset
                                return Offset.Zero
                            }
                        })
                ) {
                    item {
                        Spacer(modifier = Modifier.height(imageHeight))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE),
                                text = recipe.title,
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Text(text = recipe.type + " / " + recipe.duration)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("Ingredients")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 14.sp
                                        )
                                    ) {
                                        append(" ${recipe.servings} Serves")
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            recipe.ingredients.forEach {
                                IngredientItem(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    ingredient = it
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("Directions")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 14.sp
                                        )
                                    ) {
                                        append(" $totalSteps steps")
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            recipe.directions.forEach {
                                InstructionItem(
                                    instruction = it,
                                    totalSteps ?: 0
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        ) {
                            if (uiState.isSimilarRecipesLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .align(Alignment.Center)
                                )
                            } else {
                                if (uiState.similarRecipes.isNotEmpty()) {
                                    Column(modifier = Modifier.wrapContentSize()) {
                                        Text(
                                            text = "Similar Recipes",
                                            modifier = Modifier.padding(
                                                start = 16.dp,
                                                top = 8.dp
                                            ),
                                            style = MaterialTheme.typography.h6
                                        )
                                        LazyRow(
                                            modifier = Modifier.fillMaxSize(),
                                            contentPadding = PaddingValues(8.dp),
                                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                                        ) {
                                            items(uiState.similarRecipes) {
                                                RecipeItem(
                                                    recipe = it,
                                                    modifier = Modifier.size(
                                                        280.dp,
                                                        240.dp
                                                    ),
                                                    onClick = { recipe ->
                                                        onRecipeClick(recipe)
                                                        scope.launch {
                                                            hasScrolledUp = true
                                                            listState.animateScrollToItem(0)
                                                        }

                                                    }
                                                ){}
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(52.dp))
                    }
                }

                AnimatedVisibility(
                    visible = shouldShowBottomTab,
                    modifier = Modifier.align(Alignment.BottomEnd),
                ) {
                    BottomTab(
                        modifier = Modifier.fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                        onSaveRecipeClick = {
                            onSaveRecipeClick(recipe)
                        }, onCookRecipeClick = {
                            onCookRecipeClick(recipe)
                        })
                }
            }
        }
    }
}


@Composable
fun BoxScope.BottomTab(
    modifier: Modifier,
    onSaveRecipeClick: () -> Unit,
    onCookRecipeClick: () -> Unit
) {
    Row(
        modifier = modifier.align(Alignment.BottomCenter),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ElevatedButton(
            modifier = Modifier
                .weight(0.75f)
                .height(48.dp),
            onClick = onSaveRecipeClick
        ) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.width(16.dp))
        ElevatedButton(
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            onClick = onCookRecipeClick
        ) {
            Text(text = "Cook This Dish")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier.height(24.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Cook This Dish"
            )
        }
    }

}
