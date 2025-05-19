package org.example.recipes.core.network.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.map
import org.example.recipes.core.network.IAuthService


class AuthService : IAuthService {
    override val uid = Firebase.auth.authStateChanged.map { it?.uid }

    override suspend fun registerUser(
        email: String,
        password: String,
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun signInUser(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
    }


}

