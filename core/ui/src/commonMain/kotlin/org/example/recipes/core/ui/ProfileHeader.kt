package org.example.recipes.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.recipes.core.model.Profile

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    profile: Profile,
    onUpdateProfileClicked: () -> Unit
) {
    Row(modifier = modifier) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = profile.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(CircleShape)
                .size(140.dp)
                /*.border(
                    width = 2.dp,
                    //color = Color.Black,
                    shape = CircleShape
                )*/

        )
        Spacer(modifier = Modifier.width(24.dp))
        Box(Modifier.fillMaxHeight().padding(top = 16.dp)) {
            Column {
                Text(
                    text = profile.name,
                    fontSize = 24.sp
                )
                Text(
                    text = profile.email,
                    fontSize = 18.sp,
                    modifier = Modifier.alpha(0.7f)
                )
            }
            Button(
                modifier = Modifier.align(Alignment.BottomStart),
                contentPadding = PaddingValues(4.dp),
                onClick = onUpdateProfileClicked,
            ) {
                Text(text = "Update Profile")
            }
        }

    }
}