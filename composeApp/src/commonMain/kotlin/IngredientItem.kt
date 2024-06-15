
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.alexzhirkevich.cupertino.CupertinoText

data class Ingredient(val title: String, val image: String, val quantity: String)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IngredientItem(
    modifier: Modifier,
    ingredient: Ingredient
) {
    ListItem(
        modifier = modifier,
        icon = {
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = ingredient.image,
                contentDescription = ingredient.title,
                contentScale = ContentScale.FillBounds
            )
        },
        text = {
            CupertinoText(
                text = ingredient.title,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 18.sp
            )
        },
        trailing = {
            CupertinoText(
                text = ingredient.quantity,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
            )
        }
    )
}
