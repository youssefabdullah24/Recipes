
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun App() {
    AppTheme {
        RecipesScreen(
            onRecipeClick = {},
            onQuickSearchItemClick = {},
            modifier = Modifier.fillMaxSize()
        )

    }
}

