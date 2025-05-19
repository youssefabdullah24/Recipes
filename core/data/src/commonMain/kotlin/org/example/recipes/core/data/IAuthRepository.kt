package org.example.recipes.core.data

import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    val userId: Flow<String?>

    suspend fun registerUser(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun signInUser(
        email: String,
        password: String,
        remember: Boolean
    ): Result<Unit>

    suspend fun logout(): Result<Unit>
}