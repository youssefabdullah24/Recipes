package org.example.recipes.core.network

import kotlinx.coroutines.flow.Flow

interface IAuthService {
    val uid: Flow<String?>

    suspend fun registerUser(
        email: String,
        password: String,
    )

    suspend fun signInUser(
        email: String,
        password: String,
    )

    suspend fun logout()

}