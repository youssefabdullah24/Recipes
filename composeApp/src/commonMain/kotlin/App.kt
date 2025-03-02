import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipes.feature.cook_recipe.CookRecipeRoute
import com.slapps.cupertino.adaptive.AdaptiveNavigationBar
import com.slapps.cupertino.adaptive.AdaptiveNavigationBarItem
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.example.racipes.feature.recipes.RecipesRoute
import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Recipe
import org.example.recipes.feature.explore.ExploreRoute
import org.example.recipes.feature.recipe_details.RecipeDetailsRoute
import org.example.recipes.feature.search.SearchRoute


@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val navStack by navController.currentBackStackEntryAsState()
        val currentRoute = navStack?.destination?.route
        var isVisible by rememberSaveable { mutableStateOf(false) }
        LaunchedEffect(currentRoute) {
            isVisible = !isTopLevelDestination(currentRoute)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = BottomNavigationItem.Recipes.route
            ) {
                composable(BottomNavigationItem.Recipes.route) {
                    RecipesRoute {
                        navController.navigate(Route.RecipeDetailsScreenRoute.createRoute(it))
                    }
                }
                composable(BottomNavigationItem.Explore.route) {
                    ExploreRoute()
                }
                composable(Route.SearchScreenRoute.name) {
                    SearchRoute()
                }
                composable(
                    route = Route.RecipeDetailsScreenRoute.ROUTE,
                    arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getInt("recipeId")!!
                    RecipeDetailsRoute(modifier = Modifier.fillMaxSize(),
                        recipeId = recipeId.toString(),
                        onRecipeClick = { navController.navigate(Route.RecipeDetailsScreenRoute.createRoute(it.id)) },
                        onCookRecipeClick = { navController.navigate(Route.CookRecipeScreenRoute.createRoute(it)) },
                        onSaveRecipeClick = { }
                    )
                }
                composable(
                    route = Route.CookRecipeScreenRoute.ROUTE,
                    arguments = listOf(
                        navArgument("recipeVideoUrl") {
                            type = NavType.StringType
                            nullable = true
                        },
                        navArgument("recipeDirections") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val videoUrl = backStackEntry.arguments?.getString("recipeVideoUrl")
                    val directionsJson =
                        backStackEntry.arguments?.getString("recipeDirections")!!
                    val directions = Json.decodeFromString<List<Direction>>(directionsJson)
                    CookRecipeRoute(
                        modifier = Modifier.fillMaxSize(),
                        videoUrl = videoUrl,
                        directions = directions
                    )
                }
            }
            //TODO: improve
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + slideInHorizontally(initialOffsetX = { -it }),
                exit = fadeOut() + slideOutHorizontally(targetOffsetX = { -it })
            ) {
                AppBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    onBackPressed = {
                        navController.navigateUp()
                    }, onOptionIconClicked = {

                    })
            }
            BottomNavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                listOf(
                    BottomNavigationItem.Recipes,
                    BottomNavigationItem.Explore
                ), currentRoute
            ) { route ->
                navController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.startDestinationRoute!!) { saveState = true }
                }
            }
        }
    }
}


internal fun isTopLevelDestination(route: String?): Boolean {
    return when (route) {
        BottomNavigationItem.Recipes.route,
        BottomNavigationItem.Explore.route -> true

        else -> false
    }
}


@Composable
internal fun AppBar(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onOptionIconClicked: (Int) -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        }, actions = {
            IconButton(onClick = { onOptionIconClicked(0) }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "REPLACE"
                )
            }
        }
    )
}

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
internal fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    topLevelDestinations: List<BottomNavigationItem>,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
) {
    if (isTopLevelDestination(currentRoute)) {
        AdaptiveNavigationBar(modifier = modifier) {
            topLevelDestinations.forEach { item ->
                AdaptiveNavigationBarItem(
                    selected = currentRoute == item.route,
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title) },
                    onClick = { onNavigate(item.route) }
                )
            }
        }
    }
}

internal sealed class BottomNavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Recipes : BottomNavigationItem("recipes", "Recipes", Icons.Default.Home)
    data object Explore : BottomNavigationItem("explore", "Explore", Icons.Default.Search)
}

@Serializable
sealed class Route(val name: String) {
    @Serializable
    data object SearchScreenRoute : Route("search")

    @Serializable
    data object RecipeDetailsScreenRoute : Route("recipe_details") {
        const val ROUTE = "recipe_details/{recipeId}"
        fun createRoute(recipeId: Int): String {
            return "recipe_details/$recipeId"
        }
    }


    @Serializable
    data object CookRecipeScreenRoute : Route("cook_recipe") {
        const val ROUTE =
            "cook_recipe?recipeVideoUrl={recipeVideoUrl}&recipeDirections={recipeDirections}"

        fun createRoute(recipe: Recipe): String {
            val recipeDirectionsJson = Json.encodeToString(recipe.directions)
            return "cook_recipe?recipeVideoUrl=${recipe.videoUrl}&recipeDirections=$recipeDirectionsJson"
        }
    }
}

