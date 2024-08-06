import org.example.recipes.core.data.di.dataModule
import org.example.recipes.core.network.di.networkModule
import org.koin.core.context.startKoin


fun initKoin() = startKoin {
    modules(
        networkModule,
        dataModule,
        viewModelModule
    )
}