
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveNavigationBar
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveNavigationBarItem
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.example.racipes.feature.recipes.RecipesTab
import org.example.racipes.feature.recipes.navigation.featureRecipesScreenModule
import org.example.recipes.core.ui.VideoPlayer
import org.example.recipes.feature.explore.ExploreTab
import org.example.recipes.feature.explore.navigation.featureExploreScreenModule
import org.example.recipes.feature.recipe_details.navigation.featureRecipeDetailsScreenModule
import org.example.recipes.feature.search.navigation.featureSearchScreenModule

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
internal fun App() {
    AppTheme {
        ScreenRegistry {
            featureRecipeDetailsScreenModule()
            featureRecipesScreenModule()
            featureExploreScreenModule()
            featureSearchScreenModule()
        }

        TabNavigator(tab = RecipesTab) {
            AdaptiveScaffold(
                modifier = Modifier.fillMaxSize(),
                snackbarHost = {

                },
                content = {
                    Box(modifier = Modifier.padding(it)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    AdaptiveNavigationBar {
                        TabNavigationItem(tab = RecipesTab)
                        TabNavigationItem(tab = ExploreTab)
                    }
                }
            )
        }
    }
}


@OptIn(ExperimentalAdaptiveApi::class)
@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    AdaptiveNavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(text = tab.options.title) },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
    )
}
