
import org.example.racipes.feature.recipes.di.recipesModule
import org.example.recipes.core.data.di.dataModule
import org.example.recipes.core.network.di.networkModule
import org.example.recipes.feature.explore.di.exploreModule
import org.example.recipes.feature.search.di.searchModule
import org.koin.core.context.startKoin


fun initKoin() = startKoin {
    modules(
        networkModule,
        dataModule,
        recipesModule,
        exploreModule,
        searchModule,
    )
}
