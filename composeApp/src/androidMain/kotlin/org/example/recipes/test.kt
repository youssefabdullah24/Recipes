package org.example.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SummerBerryCakeScreen() {
    val scrollState = rememberScrollState()
    val imageHeight = 360.dp
    val cardMinHeight = 60.dp
    val cardMaxHeight = 200.dp
    val scrollOffset = remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Image Background
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )

        // Scrollable Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(object : NestedScrollConnection {
                    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                        val delta = available.y
                        val newOffset = (scrollOffset.value + delta).coerceIn(
                            with(density) { -imageHeight.toPx() + cardMinHeight.toPx() },
                            0f
                        )
                        scrollOffset.value = newOffset
                        return Offset.Zero
                    }
                })
        ) {
            item {
                Spacer(modifier = Modifier.height(imageHeight))
                // Content below the card
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    repeat(50) {
                        Text(
                            text = "Content Item $it",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // Expanding Card
/*        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    with(density) {
                        (imageHeight + scrollOffset.value.toDp()).coerceIn(cardMinHeight, cardMaxHeight)
                    }
                )
                .align(Alignment.BottomCenter)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 8.dp,
                backgroundColor = Color.White
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Summer Berry Cake",
                            style = MaterialTheme.typography.h6,
                            color = Color.Black
                        )
                        Text(
                            text = "Breakfast / 30 mins",
                            style = MaterialTheme.typography.body2,
                            color = Color.Gray
                        )
                    }
                }
            }
        }*/
    }
}


