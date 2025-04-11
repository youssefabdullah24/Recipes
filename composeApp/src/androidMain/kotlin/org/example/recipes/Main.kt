package org.example.recipes

import android.app.Application
import com.google.firebase.FirebaseApp
import initKoin

class Main : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        FirebaseApp.initializeApp(this)
    }
}