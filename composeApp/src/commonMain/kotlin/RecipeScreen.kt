import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.alexzhirkevich.cupertino.CupertinoText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeScreen(
    recipe: Recipe,
    modifier: Modifier
) {

    // TODO: replace with CollapsingToolbar
    // TODO: add Buttons
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            model = recipe.image,
            contentScale = ContentScale.FillBounds,
            contentDescription = recipe.title
        )
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

        LazyColumn(modifier = Modifier.padding(8.dp)) {

            item {
                Column(modifier = modifier) {
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
                }
            }
            items(recipe.ingredients) {
                IngredientItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    ingredient = it
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            val totalSteps = recipe.instructions.size

            item {
                Column(modifier = modifier) {
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
                }
            }
            items(recipe.instructions) {
                InstructionItem(instruction = it, totalSteps)
            }

        }
    }
}
