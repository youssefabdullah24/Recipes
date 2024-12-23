@file:OptIn(ExperimentalMaterialApi::class)

package org.example.recipes.core.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.recipes.core.model.*

@OptIn(ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class)
@Composable
fun TrendingItem(
    recipe: Recipe,
    onClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Card(
            onClick = { onClick(recipe) },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(top = 80.dp), // Add padding to leave space for the image overlap
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 42.dp)
            ) {
                Spacer(modifier = Modifier.height(42.dp)) // Space to match the image overlap
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.basicMarquee(Int.MAX_VALUE)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = recipe.type,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.alpha(0.6f),
                    text = recipe.duration,
                    fontSize = 14.sp,
                )
            }
        }
        AsyncImage(
            model = recipe.image,
            contentDescription = recipe.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 8.dp)
                .size(160.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter)
        )
    }
}
