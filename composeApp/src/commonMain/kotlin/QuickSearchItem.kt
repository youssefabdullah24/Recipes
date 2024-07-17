
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.alexzhirkevich.cupertino.CupertinoText


data class QuickSearchItem(val title: String, val image: String)

@Composable
fun QuickSearchItem(
    modifier: Modifier = Modifier,
    quickSearchItem: QuickSearchItem
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                modifier = Modifier.size(100.dp).clip(CircleShape),
                model = quickSearchItem.image,
                contentDescription = quickSearchItem.title,
                contentScale = ContentScale.FillBounds
            )
            CupertinoText(text = quickSearchItem.title)
        }
    }
}