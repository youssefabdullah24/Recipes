package org.example.recipes.core.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,
    val image: String,
    val servings: String,
    val type: String,
    //val nutrition: Nutrition, ...inlined
    val calories: Int,
    val carbohydrates: Int,
    val fat: Int,
    val fiber: Int,
    val protein: Int,
    val sugar: Int,
    //one-many
    //val directions: List<Direction>, ...DONE
    //val ingredients: List<Ingredient>, ...DONE
    val videoUrl: String?,
    //val tags: List<Tag>, ...DONE
    val updatedAt: Int,
    val createdAt: Int,
    val ratingPositive: Int,
    val ratingNegative: Int,
    val ratingScore: Double,
    var isFavorite: Boolean = false,
    var hasCooked: Boolean = false,
    val numServings: Int
)


data class RecipeDetails(
    @Embedded
    val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id", // recipe->id
        entityColumn = "recipeId" // direction->recipeId
    )
    val directions: List<DirectionEntity>,
    @Relation(
        parentColumn = "id", // recipe->id
        entityColumn = "recipeId" // ingredient->recipeId
    )
    val ingredients: List<IngredientEntity>,
    @Relation(
        parentColumn = "id", // recipe->id
        entityColumn = "displayName", // tag->displayName
        associateBy = Junction(RecipeTagCrossRef::class)
    )
    val tags: List<TagEntity>
)

@Entity(primaryKeys = ["id", "displayName"])
data class RecipeTagCrossRef(
    val id: Int,
    val displayName: String
)
