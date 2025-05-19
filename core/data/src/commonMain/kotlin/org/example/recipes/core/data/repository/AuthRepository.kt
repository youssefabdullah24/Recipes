package org.example.recipes.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.recipes.core.data.IAuthRepository
import org.example.recipes.core.network.IAuthService

class AuthRepository(private val authService: IAuthService) : IAuthRepository {
    override val userId: Flow<String?>
        get() = authService.uid

    override suspend fun registerUser(
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            authService.registerUser(
                email,
                password,
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun signInUser(
        email: String,
        password: String,
        remember: Boolean
    ): Result<Unit> {
        return try {
            authService.signInUser(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            authService.logout()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}