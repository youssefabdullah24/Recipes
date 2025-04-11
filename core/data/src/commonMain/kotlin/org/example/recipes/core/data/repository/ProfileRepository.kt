package org.example.recipes.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FieldValue
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.recipes.core.data.IProfileRepository
import org.example.recipes.core.model.Profile

class ProfileRepository : IProfileRepository {
    override fun getProfile(uid: String): Result<Flow<Profile?>> {
        return try {
            Result.success(
                Firebase.firestore.collection("Profiles")
                    .document(uid)
                    .snapshots.map {
                        it.data<Profile?>()?.apply {
                            favorites.sortedDescending()
                        }
                    })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun updateProfile(
        uid: String,
        profile: Profile
    ): Result<Unit> {
        return try {
            Firebase
                .firestore
                .collection("Profiles")
                .document(uid)
                .set(
                    mapOf(
                        "name" to profile.name,
                        "email" to profile.email,
                        "image" to profile.image
                    )
                )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun toggleRecipe(
        uid: String,
        recipeId: String,
    ): Result<Unit> {
        return try {
            val doc = Firebase
                .firestore
                .collection("Profiles")
                .document(uid)
            val favorites = doc.get().get<List<String>>("favorites")
            if (favorites.contains(recipeId)) {
                doc.update(Pair("favorites", FieldValue.arrayRemove(recipeId)))
            } else {
                doc.update(Pair("favorites", FieldValue.arrayUnion(recipeId)))
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
