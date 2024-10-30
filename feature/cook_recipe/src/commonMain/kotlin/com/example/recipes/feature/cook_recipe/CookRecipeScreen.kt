package com.example.recipes.feature.cook_recipe

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.ui.Step
import org.example.recipes.core.ui.VideoPlayer


data class CookRecipeScreen(
    val modifier: Modifier,
    val recipe: Recipe
) : Screen {

    @Composable
    override fun Content() {
        var currentStep by rememberSaveable { mutableStateOf(1) }
        val lastStep by rememberSaveable { mutableStateOf(recipe.directions.size) }
        val currentDirection by mutableStateOf(recipe.directions[currentStep - 1])
        Box(modifier = modifier) {
            Column(modifier = Modifier.fillMaxSize()) {
                recipe.videoUrl?.let {
                    VideoPlayer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f),
                        url = it,
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Step $currentStep"
                    )
                    LazyRow(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(recipe.directions) {
                            Step(
                                modifier = Modifier.size(32.dp),
                                stepNumber = it.position,
                                isSelected = it.position == currentStep
                            ) { step ->
                                currentStep = step
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
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AnimatedVisibility(currentStep > 1) {
                    Row(modifier = Modifier.weight(0.25f)) {
                        ElevatedButton(
                            onClick = { currentStep-- },
                            modifier = Modifier
                                .height(48.dp)
                        ) {
                            Icon(
                                modifier = Modifier.height(24.dp),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Previous Step"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Previous")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }

                ElevatedButton(
                    modifier = Modifier
                        .weight(0.75f)
                        .height(48.dp),
                    onClick = { currentStep++ }
                ) {
                    Text(text = if (currentStep == lastStep) "Finish Cook" else "Next Step")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.height(24.dp),
                        imageVector = if (currentStep == lastStep) Icons.Default.Check else Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next Step"
                    )
                }
            }
        }


    }

}

