import org.example.recipes.core.network.di.networkModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(networkModule)
}