package com.example.recipes.feature.cook_recipe.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.screenModule
import com.example.recipes.feature.cook_recipe.CookRecipeScreen
import com.example.recipes.navigation.Routes

val featureCookRecipeScreenModule = screenModule {
    register<Routes.CookRecipeScreenRoute> {
        CookRecipeScreen(
            modifier = Modifier.fillMaxSize(),
            recipe = it.recipe
        )
    }
}
