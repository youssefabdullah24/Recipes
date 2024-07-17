
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Bookmark
import compose.icons.fontawesomeicons.regular.Clock
import compose.icons.fontawesomeicons.regular.Heart
import compose.icons.fontawesomeicons.regular.User
import io.github.alexzhirkevich.cupertino.CupertinoText


data class Recipe(
    val title: String,
    val duration: String,
    val image: String,
    val servings: String,
    val type: String,
    //val description: String,
    val ingredients: List<Ingredient>,
    val instructions: List<Instruction>
)

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeItem(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: (Recipe) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick(recipe) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()) {
           /* Box(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(Color.Red)
            )*/
               AsyncImage(
                   modifier = Modifier.fillMaxWidth()
                       .fillMaxHeight(0.5f),
                   model = recipe.image,
                   contentDescription = recipe.title,
                   onLoading = {
                       // TODO: Show a loading indicator while the image is loading

                   },
                   onError = {
                       // TODO: Handle any errors that occur while loading the image

                   }
               )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                CupertinoText(
                    modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE),
                    text = recipe.title,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1)

                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                RecipeDetailRow(
                    icon = FontAwesomeIcons.Regular.Clock,
                    label = "Duration",
                    value = recipe.duration
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                RecipeDetailRow(
                    icon = FontAwesomeIcons.Regular.User,
                    label = "Servings",
                    value = recipe.servings
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Row(modifier = Modifier.fillMaxWidth()){
                    RecipeDetailRow(
                        modifier = Modifier.weight(1f),
                        icon = FontAwesomeIcons.Regular.Bookmark,
                        label = "Type",
                        value = recipe.type
                    )

                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { /* TODO: Handle click event */}){
                        Icon(
                            modifier = Modifier.fillMaxSize().padding(4.dp),
                            imageVector = FontAwesomeIcons.Regular.Heart,
                            contentDescription = "Add to Favorites"
                        )
                    }

                }


            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeDetailRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(modifier = modifier.height(18.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(16.dp),
            imageVector = icon,
            contentDescription = label
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        CupertinoText(
            modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE),
            text = value,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1)
    }
}