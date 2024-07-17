
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.SlidersH
import io.github.alexzhirkevich.cupertino.CupertinoText


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit
) {
    Card {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp)
                    .alpha(0.6f),
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
            Spacer(modifier = Modifier.width(8.dp))
            CupertinoText(
                modifier = Modifier.alpha(0.6f)
                    .clickable { onSearchClicked() },
                text = "What are you craving?"
            )
            Spacer(Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(24.dp)
                    .alpha(0.6f)
                    .clickable { onFilterClicked() },
                imageVector = FontAwesomeIcons.Solid.SlidersH,
                contentDescription = "Filter"
            )
        }
    }

}