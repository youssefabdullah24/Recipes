package org.example.recipes.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Step(
    modifier: Modifier,
    stepNumber: Int,
    isSelected: Boolean,
    onStepClick: (stepNumber: Int) -> Unit
) {
    Card(
        onClick = {
            onStepClick(stepNumber)
        },
        modifier = modifier.alpha(if (isSelected) 1f else 0.5f),
        border = BorderStroke(
            2.dp,
            Color.Black
        ),
        shape = RoundedCornerShape(percent = 100)
    ) {
        Text(
            text = stepNumber.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)

        )
    }
}