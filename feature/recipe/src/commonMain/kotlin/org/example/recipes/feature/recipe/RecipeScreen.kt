package org.example.recipes.feature.recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.alexzhirkevich.cupertino.CupertinoText
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.ui.IngredientItem
import org.example.recipes.core.ui.InstructionItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeScreen(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    val imageHeight = 360.dp
    val cardMinHeight = 60.dp
    val scrollOffset = remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    val totalSteps = recipe.directions.size


    Box(modifier = modifier) {
        // Image Background
        AsyncImage(
            model = recipe.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight + with(density) { scrollOffset.value.toDp() })
            // .fillMaxHeight(0.6f)
        )

        // Scrollable Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(object : NestedScrollConnection {
                    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                        val delta = available.y
                        val newOffset = (scrollOffset.value + delta).coerceIn(
                            with(density) { -imageHeight.toPx() + cardMinHeight.toPx() },
                            0f
                        )
                        scrollOffset.value = newOffset
                        return Offset.Zero
                    }
                })
        ) {
            item {
                Spacer(modifier = Modifier.height(imageHeight))
                // Content below the card

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CupertinoText(
                        modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE),
                        text = recipe.title,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    CupertinoText(text = recipe.type + " / " + recipe.duration)
                    Spacer(modifier = Modifier.height(8.dp))


                    CupertinoText(
                        text = buildAnnotatedString {
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            ) {
                                append("Ingredients")
                            }
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
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



                    CupertinoText(
                        text = buildAnnotatedString {
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            ) {
                                append("Directions")
                            }
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    fontSize = 14.sp
                                )
                            ) {
                                append(" $totalSteps steps")
                            }
                        }
                    )



                    Spacer(modifier = Modifier.height(16.dp))


                    recipe.directions.forEach {
                        InstructionItem(instruction = it, totalSteps)
                    }
                }
            }
        }
    }
}
