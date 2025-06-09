package org.example.recipes.core.ui
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
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
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = ingredient.name, //TODO
                contentDescription = ingredient.name,
                contentScale = ContentScale.FillBounds
            )
        },
        text = {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )){
                        append(ingredient.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() })
                    }
                    withStyle(style = SpanStyle(
                        fontSize = 14.sp,
                    )){
                        append("   ${ingredient.extraComment}")
                    }
                },
            )
        },
        trailing = {
            if(ingredient.measurement.quantity != "0") {
                Text(
                    text = "${ingredient.measurement.quantity} ${ingredient.measurement.abbreviation}",
                    style = MaterialTheme.typography.body2,
                    fontSize = 14.sp
                )
            }
        }
    )
}
