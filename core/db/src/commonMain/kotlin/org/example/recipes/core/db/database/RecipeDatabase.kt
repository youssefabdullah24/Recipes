package org.example.recipes.core.db.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.example.recipes.core.db.dao.RecipeDao
import org.example.recipes.core.db.entity.DirectionEntity
import org.example.recipes.core.db.entity.IngredientEntity
import org.example.recipes.core.db.entity.RecipeEntity
import org.example.recipes.core.db.entity.RecipeTagCrossRef
import org.example.recipes.core.db.entity.TagEntity

@Database(
    entities = [
        RecipeEntity::class,
        DirectionEntity::class,
        IngredientEntity::class,
        TagEntity::class,
        RecipeTagCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RecipeDatabase> {
    override fun initialize(): RecipeDatabase
}

