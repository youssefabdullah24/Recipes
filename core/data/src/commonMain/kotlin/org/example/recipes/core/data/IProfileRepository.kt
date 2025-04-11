package org.example.recipes.core.data

import kotlinx.coroutines.flow.Flow
import org.example.recipes.core.model.Profile

interface IProfileRepository {
    fun getProfile(uid: String): Result<Flow<Profile?>>
    suspend fun updateProfile(
        uid: String,
        profile: Profile
    ) : Result<Unit>

    suspend fun toggleRecipe(
        uid: String,
        recipeId: String,
    ) : Result<Unit>

    suspend fun addToCookedRecipes(
        uid: String,
        recipeId: String,
    ) : Result<Unit>
}