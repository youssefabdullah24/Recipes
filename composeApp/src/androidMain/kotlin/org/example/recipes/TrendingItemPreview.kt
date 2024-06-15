import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TrendingItemPreview() {
    AppTheme {
        Surface {
            TrendingItem(
                recipe = Recipe(
                    title = "",
                    duration = "ullamcorper",
                    image = "nisi",
                    servings = "delenit",
                    type = "offendit"
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .height(280.dp)
            )
        }
    }

}