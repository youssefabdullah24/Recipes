package org.example.recipes.core.db

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.recipes.core.db.database.RecipeDatabase

fun getRoomDatabase(builder: RoomDatabase.Builder<RecipeDatabase>): RecipeDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}