package org.example.recipes.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Profile

@Preview
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(16.dp),
        profile = Profile(
            name = "xaxa",
            email = "ccccc@x.com",
            image = "no",
            favorites = listOf()
        ),
        onUpdateProfileClicked = {}
    )
}