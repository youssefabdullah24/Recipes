import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.touchlab.kermit.Logger
import com.slapps.cupertino.adaptive.AdaptiveNavigationBar
import com.slapps.cupertino.adaptive.AdaptiveNavigationBarItem
import com.slapps.cupertino.adaptive.AdaptiveScaffold
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.example.racipes.feature.recipes.RecipesRoute
import org.example.recipes.core.data.ProfileViewModel
import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Profile
import org.example.recipes.core.ui.appbar.AppbarState
import org.example.recipes.core.ui.appbar.LocalAppBarState
import org.example.recipes.feature.cook_recipe.CookRecipeRoute
import org.example.recipes.feature.explore.ExploreRoute
import org.example.recipes.feature.profile.ProfileRoute
import org.example.recipes.feature.profile.RegisterScreen
import org.example.recipes.feature.recipe_details.RecipeDetailsRoute
import org.example.recipes.feature.recipe_reviews.RecipeReviewsRoute
import org.example.recipes.feature.search.SearchRoute
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun App(
    profileViewModel: ProfileViewModel = koinViewModel(),
    onFullScreenPressed: (Boolean) -> Unit
) {
    val appBarState = remember { AppbarState() }
    AppTheme {
        CompositionLocalProvider(LocalAppBarState provides appBarState) {
            val navController = rememberNavController()
            val navStack by navController.currentBackStackEntryAsState()
            val currentRoute = navStack?.destination?.route
            val profileUiState by profileViewModel.profileUiState.collectAsState()
            val uid by profileViewModel.uid.collectAsState()
            val favorites = profileUiState.favorites
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()
            val connectivity = Connectivity {
                autoStart = true
            }

            val connectionState by connectivity.statusUpdates.collectAsState(initial = Connectivity.Status.Disconnected)
            LaunchedEffect(connectionState) {
                when (connectionState) {
                    is Connectivity.Status.Connected -> {
                        Logger.d(tag = "Connectivity", messageString = "Connected")
                    }

                    is Connectivity.Status.Disconnected -> {
                        Logger.d(tag = "Connectivity", messageString = "Disconnected")
                        showSnackBar(
                            snackbarHostState,
                            scope,
                            "You're Offline",
                            duration = SnackbarDuration.Indefinite,
                            dismissable = true
                        )
                    }
                }
            }

            AdaptiveScaffold(
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                },
                bottomBar = {
                    BottomNavigationBar(
                        topLevelDestinations = listOf(
                            BottomNavigationItem.Recipes,
                            BottomNavigationItem.Explore,
                            BottomNavigationItem.Profile
                        ),
                        currentRoute = currentRoute
                    ) { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationRoute!!) {
                                saveState = true
                            }
                        }
                    }
                }
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = it.calculateBottomPadding())
                ) {
                    Box {
                        NavHost(
                            navController = navController,
                            startDestination = BottomNavigationItem.Recipes.route
                        ) {
                            composable(BottomNavigationItem.Recipes.route) {
                                RecipesRoute(
                                    favorites = favorites,
                                    onRecipeClicked = {
                                        navController.navigate(RecipeDetailsScreenRoute(it))
                                    }, onAddToFavoritesClicked = { recipeId ->
                                        if (uid == null) {
                                            showSnackBar(
                                                snackbarHostState,
                                                scope,
                                                "Please login first."
                                            )
                                        } else {
                                            profileViewModel.toggleFavoriteRecipe(recipeId)
                                        }
                                    }
                                )
                            }
                            composable(BottomNavigationItem.Explore.route) {
                                ExploreRoute(
                                    isConnected = connectionState.isConnected,
                                    onNavigate = {
                                        navController.navigate(SearchScreenRoute(null))
                                    },
                                    onQuickSearchItemClick = {
                                        navController.navigate(SearchScreenRoute(it))
                                    })
                            }
                            composable(BottomNavigationItem.Profile.route) {
                                ProfileRoute(
                                    viewModel = profileViewModel,
                                    onRecipeClicked = {
                                        navController.navigate(RecipeDetailsScreenRoute(it))
                                    }, onUpdateProfileClicked = {

                                    }, onRegisterClicked = {
                                        navController.navigate(RegisterRoute)
                                    })
                            }
                            composable<RegisterRoute> {
                                RegisterScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    viewModel = profileViewModel,
                                    onRegisterClicked = { name, email, password ->
                                        profileViewModel.registerUser(
                                            Profile(name, email, null, emptyList(), emptyList()),
                                            password
                                        )
                                    }, onRegistered = {
                                        navController.navigate(
                                            BottomNavigationItem.Profile.route,
                                        )
                                    }
                                )
                            }
                            composable<SearchScreenRoute> { backStackEntry ->
                                val args: SearchScreenRoute = backStackEntry.toRoute()
                                SearchRoute(
                                    quickSearchQuery = args.query,
                                    favorites = favorites.map { it.id.toString() },
                                    onRecipeClick = {
                                        navController.navigate(RecipeDetailsScreenRoute(it))
                                    },
                                    onAddToFavoritesClicked = {
                                        if (uid == null) {
                                            showSnackBar(
                                                snackbarHostState,
                                                scope,
                                                "Please login first."
                                            )
                                        } else {
                                            profileViewModel.toggleFavoriteRecipe(it)
                                        }
                                    },
                                    onBackPressed = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                            composable<RecipeDetailsScreenRoute> { backStackEntry ->
                                val args: RecipeDetailsScreenRoute = backStackEntry.toRoute()
                                RecipeDetailsRoute(
                                    modifier = Modifier.fillMaxSize(),
                                    recipeId = args.recipeId.toString(),
                                    favorites = favorites.map { it.id.toString() },
                                    isConnected = connectionState.isConnected,
                                    onRecipeClick = {
                                        navController.navigate(
                                            RecipeDetailsScreenRoute(it.id)
                                        )
                                    },
                                    onCookRecipeClick = {
                                        navController.navigate(
                                            CookRecipeScreenRoute(
                                                it.id,
                                                it.videoUrl,
                                                Json.encodeToString(it.directions),
                                            )
                                        )
                                    },
                                    onViewAllReviewsClicked = { id, name ->
                                        navController.navigate(RecipeReviewsScreenRoute(id, name))
                                    }, onSaveRecipeClick = { recipe ->
                                        if (uid == null) {
                                            showSnackBar(
                                                snackbarHostState,
                                                scope,
                                                "Please login first."
                                            )
                                        } else {
                                            profileViewModel.toggleFavoriteRecipe(recipe)
                                        }
                                    }, onBackPressed = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                            composable<RecipeReviewsScreenRoute> { backStackEntry ->
                                val args: RecipeReviewsScreenRoute = backStackEntry.toRoute()
                                RecipeReviewsRoute(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 56.dp),
                                    recipeName = args.recipeName,
                                    recipeId = args.recipeId,
                                    onBackPressed = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                            composable<CookRecipeScreenRoute> { backStackEntry ->
                                val args: CookRecipeScreenRoute = backStackEntry.toRoute()
                                val directions =
                                    Json.decodeFromString<List<Direction>>(args.directions)
                                CookRecipeRoute(
                                    modifier = Modifier.fillMaxSize(),
                                    videoUrl = args.videoUrl!!,
                                    directions = directions,
                                    onFinishCooking = {
                                        navController.navigate(BottomNavigationItem.Recipes.route)
                                        profileViewModel.addToCookedRecipes(args.recipeId.toString())
                                    }, onBackPressed = {
                                        navController.navigateUp()
                                    }, onDownloadVideoPressed = {
                                        Logger.d("Download URL: ${it}")
                                    },
                                    onFullScreenPressed = onFullScreenPressed

                                )
                            }
                        }

                        if (appBarState.isVisible){
                            AppBar(
                                title = appBarState.title,
                                showBackground = appBarState.showBackground,
                                onBackPressed = { navController.navigateUp() },
                                actions = appBarState.actions
                            )
                        }
                    }
                }
            }
        }
    }
}


internal fun isTopLevelDestination(route: String?): Boolean {
    return when (route) {
        BottomNavigationItem.Recipes.route,
        BottomNavigationItem.Explore.route,
        BottomNavigationItem.Profile.route,
            -> true

        else -> false
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppBar(
    modifier: Modifier = Modifier,
    title: String?,
    showBackground: Boolean,
    onBackPressed: () -> Unit,
    actions : @Composable (RowScope.() -> Unit) = {},
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (showBackground)
                MaterialTheme.colorScheme.onPrimaryContainer
            else Color.Transparent
        ),
        title = {
            title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            FilledIconButton(
                onClick = onBackPressed,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        }, actions = actions
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
    AnimatedVisibility(
        visible = isTopLevelDestination(currentRoute),
        modifier = modifier
    ) {
        AdaptiveNavigationBar {
            topLevelDestinations.forEach { item ->
                val selectedColor = if (currentRoute == item.route)
                    MaterialTheme.colorScheme.primary
                else
                    LocalContentColor.current
                AdaptiveNavigationBarItem(
                    selected = currentRoute == item.route,
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = selectedColor
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = selectedColor
                        )
                    },
                    onClick = {
                        onNavigate(item.route)
                    }
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
    data object Profile : BottomNavigationItem("profile", "Profile", Icons.Default.Person)
}


@Serializable
object LoginRoute

@Serializable
object RegisterRoute

@Serializable
data class RecipeDetailsScreenRoute(val recipeId: Int)

@Serializable
data class CookRecipeScreenRoute(
    val recipeId: Int,
    val videoUrl: String?,
    val directions: String,
)


@Serializable
data class RecipeReviewsScreenRoute(
    val recipeId: String,
    val recipeName: String
)

@Serializable
data class SearchScreenRoute(val query: String?)


fun showSnackBar(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short,
    dismissable: Boolean = false
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            message = message,
            duration = duration,
            withDismissAction = dismissable,
        )
    }
}