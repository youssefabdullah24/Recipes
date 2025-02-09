package org.example.recipes

import android.app.Application
import initKoin

class Main : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}