import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.racipes.feature.recipes.RecipesScreen

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

