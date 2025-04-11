package org.example.recipes.feature.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import org.example.recipes.core.data.ProfileUiState
import org.example.recipes.core.model.Profile

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        Surface {
            ProfileScreen(
                modifier = Modifier.fillMaxSize(),
                profileUiState = ProfileUiState(
                    profile = Profile(
                        name = "Lean",
                        email = "Natisha",
                        image = null,
                        favorites = emptyList(),
                        cooked = emptyList()
                    ),
                ),
                onUpdateProfileClicked = {},
                onRecipeClicked = {},
            ) { }

        }
    }

}