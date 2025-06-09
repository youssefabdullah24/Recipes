import org.example.recipes.feature.cook_recipe.di.cookRecipeModule
import org.example.racipes.feature.recipes.di.recipesModule
import org.example.recipes.core.data.di.dataModule
import org.example.recipes.core.network.di.networkModule
import org.example.recipes.feature.explore.di.exploreModule
import org.example.recipes.feature.profile.di.profileModule
import org.example.recipes.feature.recipe_details.di.recipeDetailsModule
import org.example.recipes.feature.recipe_reviews.di.recipeReviewsModule
import org.example.recipes.feature.search.di.searchModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        networkModule,
        dataModule,
        recipesModule,
        exploreModule,
        searchModule,
        recipeDetailsModule,
        recipeReviewsModule,
        cookRecipeModule,
        profileModule
    )
}
