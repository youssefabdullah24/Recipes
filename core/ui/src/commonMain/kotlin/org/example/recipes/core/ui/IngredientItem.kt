package org.example.recipes.core.ui
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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


@Composable
fun IngredientItem(
    modifier: Modifier,
    ingredient: Ingredient
) {
    ListItem(
        modifier = modifier,
        leadingContent = {
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = ingredient.name, //TODO
                contentDescription = ingredient.name,
                contentScale = ContentScale.FillBounds
            )
        },
        headlineContent = {
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
        }, trailingContent = {
            if(ingredient.measurement.quantity != "0") {
                Text(
                    text = "${ingredient.measurement.quantity} ${ingredient.measurement.abbreviation}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp
                )
            }
        }
    )
}
