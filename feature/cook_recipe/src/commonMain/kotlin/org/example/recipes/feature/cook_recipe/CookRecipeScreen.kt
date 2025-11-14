package org.example.recipes.feature.cook_recipe

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Direction
import org.example.recipes.core.ui.Step
import org.example.recipes.core.ui.VideoPlayer

@Composable
fun CookRecipeRoute(
    videoUrl: String?,
    directions: List<Direction>,
    onFinishCooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    CookRecipeScreen(
        videoUrl,
        directions,
        onFinishCooking,
        modifier,
    )
}

@Composable
internal fun CookRecipeScreen(
    videoUrl: String?,
    directions: List<Direction>,
    onFinishCooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentStep by rememberSaveable { mutableStateOf(1) }
    val lastStep by rememberSaveable { mutableStateOf(directions.size) }
    var currentDirection by remember { mutableStateOf(directions[currentStep - 1]) }
    var seekTo by rememberSaveable { mutableStateOf(0.0) }
    Box(modifier = modifier) {
        videoUrl?.let {
            VideoPlayer(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                seekTo = seekTo,
                url = it
            ) { progress ->
                directions.forEach {
                    if (progress >= it.startTime && progress < it.endTime) {
                        currentDirection = it
                    }
                }
                currentStep = currentDirection.position
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter),
        ) {
            Text(
                text = "Step $currentStep",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            LazyRow(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(directions) {
                    Step(
                        modifier = Modifier.size(32.dp),
                        stepNumber = it.position,
                        isSelected = it.position == currentStep
                    ) { step ->
                        currentStep = step
                        currentDirection = directions[step - 1]
                        seekTo = directions[step - 1].startTime.toDouble()
                    }
                }
            }
            AnimatedContent(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize()
                    .padding(16.dp),
                targetState = currentDirection.text
            ) {
                Text(
                    modifier = Modifier.verticalScroll(state = rememberScrollState()),
                    textAlign = TextAlign.Center,
                    text = currentDirection.text
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .animateContentSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AnimatedVisibility(
                modifier = Modifier.weight(0.5f),
                visible = currentStep > 1
            ) {
                ElevatedButton(
                    onClick = {
                        currentStep--
                        currentDirection = directions[currentStep - 1]
                        seekTo = directions[currentStep - 1].startTime.toDouble()
                    },
                    //colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                    modifier = Modifier.height(48.dp)
                ) {
                    Icon(
                        modifier = Modifier.height(24.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous Step"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Previous")
                }
            }
            ElevatedButton(
                modifier = Modifier
                    .weight(0.5f)
                    .height(48.dp),

                onClick = {
                    if (currentStep < lastStep) {
                        currentStep++
                        currentDirection = directions[currentStep - 1]
                        seekTo = directions[currentStep - 1].startTime.toDouble()

                    } else {
                        onFinishCooking()
                    }
                }
            ) {
                Text(
                    text = if (currentStep == lastStep) "Finish Cooking" else "Next Step"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier.height(24.dp),
                    imageVector = if (currentStep == lastStep) Icons.Default.Check else Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = if (currentStep == lastStep) "Finish Cooking" else "Next Step"
                )
            }
        }
    }
}
