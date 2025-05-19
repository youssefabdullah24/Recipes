package org.example.recipes.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import org.example.recipes.core.data.ProfileUiState
import org.example.recipes.core.data.ProfileViewModel
import org.example.recipes.core.ui.ProfileHeader
import org.example.recipes.core.ui.RecipeItem
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ProfileRoute(
    onRecipeClicked: (Int) -> Unit,
    onUpdateProfileClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel<ProfileViewModel>()
) {
    Logger.d("ProfileRoute")
    val profileUiState by viewModel.profileUiState.collectAsState()
    val uid by viewModel.uid.collectAsState()
    if (uid == null) {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            onRegisterClicked = onRegisterClicked,
            onLoginClicked = { email, password ->
                viewModel.signInUser(email, password)
            },
        )
    } else {
        ProfileScreen(
            modifier = Modifier.fillMaxSize(),
            profileUiState = profileUiState,
            onUpdateProfileClicked = onUpdateProfileClicked,
            onRecipeClicked = onRecipeClicked,
            onAddToFavoritesClicked = viewModel::toggleFavoriteRecipe,
            onLogOutClicked = viewModel::logOutUser
        )
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier,
    onRegisterClicked: () -> Unit,
    onLoginClicked: (String, String) -> Unit,
) {
    Logger.d("LoginScreen")
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.padding(8.dp)
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onLoginClicked(email, password) },
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onRegisterClicked,
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("Register")
            }
        }
    }
}

@Composable
fun RegisterScreen(
    modifier: Modifier,
    viewModel: ProfileViewModel,
    onRegisterClicked: (String, String, String) -> Unit,
    onRegistered: () -> Unit,
) {
    Logger.d("RegisterScreen")
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val uid by viewModel.uid.collectAsState()
    val state by viewModel.profileUiState.collectAsState()
    LaunchedEffect(uid) {
        if (uid != null) {
            onRegistered()
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Register",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                enabled = !state.isRegistrationLoading,
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.padding(8.dp)
            )

            TextField(
                enabled = !state.isRegistrationLoading,
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.padding(8.dp)
            )

            TextField(
                enabled = !state.isRegistrationLoading,
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                enabled = !state.isRegistrationLoading,
                onClick = { onRegisterClicked(name, email, password) }) {
                Text("Register")
            }
        }
    }
}


@Composable
fun ProfileScreen(
    modifier: Modifier,
    profileUiState: ProfileUiState,
    onUpdateProfileClicked: () -> Unit,
    onRecipeClicked: (Int) -> Unit,
    onAddToFavoritesClicked: (String) -> Unit,
    onLogOutClicked:() -> Unit,

) {
    Logger.d("ProfileScreen")
    Logger.d("$profileUiState")
    if (profileUiState.isProfileLoading) {
        Box(modifier = modifier) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )
        }
    }
    if (profileUiState.error != null) {
        Box(modifier = modifier) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = profileUiState.error.toString()
            )
        }
    }
    if (profileUiState.profile != null) {
        Column(
            modifier = modifier,
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(
                        top = 32.dp,
                        start = 16.dp,
                        end = 16.dp
                    ).weight(1f),
                    text = "Profile",
                    style = MaterialTheme.typography.h4
                )
                TextButton(onClick = onLogOutClicked){
                    Text(text = "Logout")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProfileHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(16.dp),
                profile = profileUiState.profile!!,
                onUpdateProfileClicked = onUpdateProfileClicked
            )
            if (profileUiState.profile?.favorites?.isNotEmpty()!!) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Favorites",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        profileUiState.favorites,
                        key = { it.id }
                    ) {
                        RecipeItem(
                            modifier = Modifier.size(
                                280.dp,
                                240.dp
                            ),
                            recipe = it,
                            onClick = { onRecipeClicked(it.id) },
                            onAddToFavoritesClicked = onAddToFavoritesClicked
                        )

                    }
                }
            }
            if (profileUiState.profile?.cooked?.isNotEmpty()!!) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Cooked Recipes",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        profileUiState.cookedRecipes,
                        key = { it.id }
                    ) {
                        RecipeItem(
                            modifier = Modifier.size(
                                280.dp,
                                240.dp
                            ),
                            recipe = it,
                            onClick = { onRecipeClicked(it.id) },
                            onAddToFavoritesClicked = onAddToFavoritesClicked
                        )

                    }
                }
            }
        }
    }
}


