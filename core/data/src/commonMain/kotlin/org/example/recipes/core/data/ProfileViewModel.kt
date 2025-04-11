package org.example.recipes.core.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.example.recipes.core.model.Profile
import org.example.recipes.core.model.Recipe

data class ProfileUiState(
    val isProfileLoading: Boolean = false,
    val profile: Profile? = null,
    val error: String? = null,
    val favorites: List<Recipe> = emptyList(),
    val isFavoriteListLoading: Boolean = false,
    val favoriteListError: String? = null
)

class ProfileViewModel(
    private val authRepository: IAuthRepository,
    private val recipesRepository: IRecipesRepository,
    private val profileRepository: IProfileRepository,
) : ViewModel() {

    private var _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()
    val uid = authRepository.userId.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null
    )


    init {
        viewModelScope.launch {
            uid.collect {
                if (it != null) {
                    getCurrentUser(it)
                }
            }

        }
    }

    private fun getCurrentUser(uid: String) {
        _profileUiState.update { it.copy(isProfileLoading = true) }
        viewModelScope.launch {
            val result = profileRepository.getProfile(uid)
            result.onSuccess { profileFlow ->
                profileFlow.collect { profile ->
                    val favorites = if(profile != null) getFavoritesList(profile.favorites) else emptyList()
                    _profileUiState.update {
                        it.copy(
                            isProfileLoading = false,
                            profile = profile,
                            favorites = favorites,
                            error = null
                        )
                    }
                }
            }.onFailure { throwable ->
                Logger.e(
                    tag = "CURRENT",
                    throwable = throwable,
                    messageString = throwable.message.toString()
                )
                _profileUiState.update {
                    it.copy(
                        isProfileLoading = false,
                        error = throwable.message
                    )
                }
            }
        }
    }

    private suspend fun getFavoritesList(favorites: List<String>): List<Recipe> {
        val favoritesList = arrayListOf<Recipe>()
        val jobs = favorites.map {
            val job = viewModelScope.launch {
                recipesRepository.getRecipe(it)
                    .onSuccess {
                        favoritesList.add(it)
                    }.onFailure { throwable ->
                        _profileUiState.update {
                            it.copy(
                                isFavoriteListLoading = false,
                                favoriteListError = throwable.message
                            )
                        }
                    }
            }
            job
        }
        jobs.joinAll()
        return favoritesList
    }


    fun toggleRecipe(recipeId: String) {
        viewModelScope.launch {
            if (uid.value != null) {
                profileRepository.toggleRecipe(uid.value!!, recipeId)
            }
        }
    }

    fun registerUser(profile: Profile, password: String) {
        _profileUiState.update { it.copy(isProfileLoading = true) }
        viewModelScope.launch {
            val result = authRepository.registerUser(
                email = profile.email, password = password
            )
            result.onSuccess {
                uid.value?.let {
                    val res = profileRepository.updateProfile(
                        uid = it, profile = profile
                    )
                    res.onFailure { throwable ->
                        _profileUiState.update {
                            it.copy(
                                isProfileLoading = false, error = throwable.message
                            )
                        }
                    }

                }
            }.onFailure { throwable ->
                Logger.e(tag = "REGISTER", throwable = throwable, messageString = throwable.message!!)
                _profileUiState.update {
                    it.copy(
                        isProfileLoading = false, error = throwable.message
                    )
                }
            }

        }
    }

    fun signInUser(email: String, password: String) {
        _profileUiState.update { it.copy(isProfileLoading = true) }
        viewModelScope.launch {
            val result = authRepository.signInUser(
                email = email, password = password, remember = false
            )
            result.onFailure { throwable ->
                Logger.e(tag = "LOGIN", throwable = throwable, messageString = throwable.message!!)
                _profileUiState.update {
                    it.copy(
                        isProfileLoading = false, error = throwable.message
                    )
                }
            }

        }
    }
}