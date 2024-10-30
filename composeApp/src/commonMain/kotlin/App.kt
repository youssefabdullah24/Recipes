import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.recipes.feature.cook_recipe.navigation.featureCookRecipeScreenModule
import org.example.racipes.feature.recipes.RecipesTab
import org.example.racipes.feature.recipes.navigation.featureRecipesScreenModule
import org.example.recipes.feature.explore.ExploreTab
import org.example.recipes.feature.explore.navigation.featureExploreScreenModule
import org.example.recipes.feature.recipe_details.navigation.featureRecipeDetailsScreenModule
import org.example.recipes.feature.search.navigation.featureSearchScreenModule

@Composable
fun App() {
    MaterialTheme {
        ScreenRegistry {
            featureRecipeDetailsScreenModule()
            featureRecipesScreenModule()
            featureExploreScreenModule()
            featureSearchScreenModule()
            featureCookRecipeScreenModule()
        }

        TabNavigator(tab = RecipesTab) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                snackbarHost = {

                },
                content = {
                    Box(modifier = Modifier.padding(it)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(tab = RecipesTab)
                        TabNavigationItem(tab = ExploreTab)
                    }
                }
            )
        }
    }
}


@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
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
