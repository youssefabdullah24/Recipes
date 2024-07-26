import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import org.example.recipes.core.network.IApiService
import org.example.recipes.core.network.model.RecipesResponse
import org.koin.compose.koinInject

@Composable
fun App(
    apiService: IApiService = koinInject()
) {
    AppTheme {
        /*ExploreScreen(
            quickSearchItems = listOf(
                QuickSearchItem(title = "propriae", image = "dictum"),
                QuickSearchItem(title = "propriae", image = "dictum"),
                QuickSearchItem(title = "propriae", image = "dictum"),
                QuickSearchItem(title = "propriaepropriae", image = "dictum"),
            ),
            popularTags = listOf(
                "a",
                "tesdadadadt",
                "tedadadadadadadast",
                "tdada\"dadada\"," + "est",
                "dadada",
                "dadada",
                "dadada",
                "dadada",
                "dad\"dadada\",\"dadada\",\"dadada\",ada",
            ),
            modifier = Modifier.fillMaxSize()
        )*/
        val result: RecipesResponse? by produceState<RecipesResponse?>(null) {
            value = apiService.getHomeRecipes()
        }
        when (result) {
            null -> {
                Text("Loading...")
            }

            else -> {
                LazyColumn {
                    items(result!!.results) {
                        RecipeItem(Recipe(
                            title = it.name,
                            duration = it.cookTimeMinutes.toString(),
                            image = it.thumbnailUrl,
                            servings = it.numServings.toString(),
                            type = "NONE..",
                            ingredients =
                            it.sections[0].components.map {
                                Ingredient(
                                    it.ingredient.name, it.ingredient.name,
                                    it.measurements[0].quantity + " " + it.measurements[0].measuringUnit.name
                                )
                            },
                            instructions = it.instructions.map {
                                Instruction(
                                    it.position,
                                    it.displayText,
                                    it.startTime.toLong(),
                                    it.endTime.toLong(),
                                    it.appliance ?: "",
                                    it.temperature ?: 0
                                )
                            }

                        ), onClick = {

                        }
                        )
                    }
                }
            }
        }

    }

}