package org.example.recipes.core.ui
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.alexzhirkevich.cupertino.CupertinoText
import org.example.recipes.core.model.Ingredient


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IngredientItem(
    modifier: Modifier,
    ingredient: Ingredient
) {
    ListItem(
        modifier = modifier,
        icon = {
            // TODO
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = ingredient.name,
                contentDescription = ingredient.name,
                contentScale = ContentScale.FillBounds
            )
        },
        text = {
            CupertinoText(
                text = ingredient.name,
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp
            )
        },
        trailing = {
            CupertinoText(
                text = ingredient.measurement.quantity,
                style = MaterialTheme.typography.body2,
                fontSize = 14.sp
            )
        }
    )
}
