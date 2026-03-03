package com.example.recipes.core.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.example.recipes.core.db.database.RecipeDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<RecipeDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("recipe.db")
    return Room.databaseBuilder<RecipeDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}