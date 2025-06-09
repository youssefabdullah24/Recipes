package org.example.recipes.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.datetime.Instant
import org.example.recipes.core.model.Tip

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReviewCard(
    modifier: Modifier,
    tip: Tip,
    onReviewClick: (Tip) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onReviewClick(tip) },
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = tip.authorAvatarUrl,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(50)),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Reviewer avatar"
                )
                Text(
                    text = if (tip.authorName.isNullOrBlank()) tip.authorUsername else tip.authorName!!,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (tip.tipPhotoUrl != null && tip.tipBody == null) {
                // Display photo only
                AsyncImage(
                    model = tip.tipPhotoUrl,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Review photo"
                )
            } else if (tip.tipBody != null && tip.tipPhotoUrl == null) {
                // Display review only
                Text(
                    text = tip.tipBody!!,
                    overflow = TextOverflow.Ellipsis
                )
            } else if (tip.tipBody != null && tip.tipPhotoUrl != null) {
                // Display both
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Text(
                        modifier = Modifier.weight(0.50f),
                        text = tip.tipBody!!,
                        overflow = TextOverflow.Ellipsis
                    )

                    AsyncImage(
                        modifier = Modifier.weight(0.50f).fillMaxSize(),
                        model = tip.tipPhotoUrl,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "Review photo"
                    )
                }
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "${Instant.fromEpochSeconds(tip.timestamp.toLong())}" //TODO: improve date readability
                )
                Text(
                    modifier = Modifier.alpha(0.6f),
                    text = "${tip.upvotes} upvotes"
                )
            }
        }
    }
}