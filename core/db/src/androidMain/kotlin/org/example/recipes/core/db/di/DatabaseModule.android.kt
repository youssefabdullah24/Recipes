package org.example.recipes.core.db.di

import androidx.room.RoomDatabase
import com.example.recipes.core.db.getDatabaseBuilder
import org.example.recipes.core.db.dao.RecipeDao
import org.example.recipes.core.db.database.RecipeDatabase
import org.example.recipes.core.db.getRoomDatabase
import org.koin.dsl.module

actual val dbModule = module {
    single<RoomDatabase.Builder<RecipeDatabase>> { getDatabaseBuilder(get()) }
    single<RecipeDao> { getRoomDatabase(get()).recipeDao() }
}