package org.example.recipes.core.ui

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import com.slapps.cupertino.adaptive.AdaptiveIconButton
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Bookmark
import compose.icons.fontawesomeicons.regular.Clock
import compose.icons.fontawesomeicons.regular.User
import org.example.recipes.core.model.Recipe


@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalAdaptiveApi::class,
)
@Composable
fun RecipeItem(
    recipe: Recipe,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Recipe) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick(recipe) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = recipe.image,
                    contentDescription = recipe.title,
                    onError = {
                        Logger.w(
                            "Error loading image",
                            it.result.throwable
                        )
                    }
                )
                recipe.videoUrl?.let {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Video"
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE),
                    text = recipe.title,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
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
                Row(modifier = Modifier.fillMaxWidth()) {
                    RecipeDetailRow(
                        modifier = Modifier.weight(1f),
                        icon = FontAwesomeIcons.Regular.Bookmark,
                        label = "Type",
                        value = recipe.type
                    )
                    AdaptiveIconButton(
                        modifier = Modifier
                            .size(48.dp),
                        onClick = { onAddToFavoritesClicked(recipe.id.toString()) }
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            tint = Color.Red,
                            contentDescription = "Add to Favorites"
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun RecipeDetailRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = modifier.height(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(16.dp),
            imageVector = icon,
            contentDescription = label
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            modifier = Modifier.basicMarquee(iterations = Int.MAX_VALUE),
            text = value,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}