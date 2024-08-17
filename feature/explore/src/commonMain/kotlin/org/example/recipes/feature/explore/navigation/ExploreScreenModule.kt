package org.example.recipes.feature.explore.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.recipes.navigation.Routes
import org.example.recipes.feature.explore.ExploreTab

val featureExploreScreenModule = screenModule {
    register<Routes.ExploreScreenRoute> {
        ExploreTab
    }
}
