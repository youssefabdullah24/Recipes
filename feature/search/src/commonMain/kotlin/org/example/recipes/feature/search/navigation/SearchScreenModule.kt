package org.example.recipes.feature.search.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.recipes.navigation.Routes
import org.example.recipes.feature.search.SearchRoute

val featureSearchScreenModule = screenModule {
    register<Routes.SearchScreenRoute> {
        SearchRoute()
    }
}
