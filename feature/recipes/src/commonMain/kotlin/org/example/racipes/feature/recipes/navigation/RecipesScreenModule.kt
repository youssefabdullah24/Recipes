package org.example.racipes.feature.recipes.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.recipes.navigation.Routes
import org.example.racipes.feature.recipes.RecipesTab

val featureRecipesScreenModule = screenModule {
    register<Routes.RecipesScreenRoute> {
        RecipesTab
    }
}

