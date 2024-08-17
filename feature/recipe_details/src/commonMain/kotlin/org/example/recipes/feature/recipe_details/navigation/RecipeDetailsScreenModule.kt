package org.example.recipes.feature.recipe_details.navigation


import cafe.adriel.voyager.core.registry.screenModule
import com.example.recipes.navigation.Routes
import org.example.recipes.feature.recipe_details.RecipeDetailsRoute

val featureRecipeDetailsScreenModule = screenModule {
    register<Routes.RecipeDetailsScreenRoute> {
        RecipeDetailsRoute(it.recipe)
    }
}
