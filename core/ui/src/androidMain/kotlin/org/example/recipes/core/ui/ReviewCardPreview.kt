package org.example.recipes.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Tip


@Composable
fun ReviewCard1(
    modifier: Modifier,
    tip: Tip,
    onReviewClick: (Tip) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onReviewClick(tip) },
        shape = RoundedCornerShape(16.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberVectorPainter(Icons.Default.AccountCircle),
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(50)),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Reviewer avatar"
            )
            Text(
                text = tip.authorName ?: tip.authorUsername,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (tip.tipPhotoUrl != null && tip.tipBody == null) {
            // Display photo only
            Image(
                //modifier = Modifier.fillMaxSize(),
                painter = rememberVectorPainter(Icons.Default.AccountCircle),
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
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.weight(0.50f),
                    text = tip.tipBody!!,
                    overflow = TextOverflow.Ellipsis
                )

                Image(
                    modifier = Modifier.weight(0.50f).fillMaxSize(),
                    painter = rememberVectorPainter(Icons.Default.AccountCircle),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Review photo"
                )
            }

        }
    }
}

@Preview
@Composable
private fun ReviewCard1Preview() {
    ReviewCard1(
        modifier = Modifier.width(320.dp)
            .height(180.dp),
        tip = Tip(
            authorAvatarUrl = "",
            authorName = "Adam",
            authorUsername = "adam.steve",
            tipBody = "Lorlem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam"+
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam"+
                    "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
            tipPhotoUrl = "",
            timestamp = 123123123,
            upvotes = 111
        ),
        onReviewClick = {}
    )
}