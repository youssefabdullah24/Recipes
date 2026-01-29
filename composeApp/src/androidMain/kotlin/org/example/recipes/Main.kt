package org.example.recipes

import android.app.Application
import appModules
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Main : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Main)
            modules(appModules)
        }
        FirebaseApp.initializeApp(this)
    }
}