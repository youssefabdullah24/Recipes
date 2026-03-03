package org.example.recipes.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import org.example.recipes.core.db.entity.DirectionEntity
import org.example.recipes.core.db.entity.IngredientEntity
import org.example.recipes.core.db.entity.RecipeDetails
import org.example.recipes.core.db.entity.RecipeEntity
import org.example.recipes.core.db.entity.RecipeTagCrossRef
import org.example.recipes.core.db.entity.TagEntity

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipes(vararg recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTags(vararg tag: TagEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipeTagCrossRef(recipeTagCrossRef: RecipeTagCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDirection(vararg direction: DirectionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(vararg ingredient: IngredientEntity)

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Transaction
    @Query("select * from recipes")
    suspend fun getAllRecipes(): List<RecipeDetails>

    @Transaction
    @Query("select * from recipes where id = :recipeId")
    suspend fun getRecipe(recipeId: Int): RecipeDetails


}