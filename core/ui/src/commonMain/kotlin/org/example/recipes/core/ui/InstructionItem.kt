package org.example.recipes.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.recipes.core.model.Direction


@Composable
fun Instructions(
    instructions: List<Direction>,
    modifier: Modifier = Modifier
) {
    val totalSteps = instructions.size
    Column(modifier = modifier) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                ) {
                    append("Directions")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp
                    )
                ) {
                    append(" $totalSteps steps")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            instructions.forEach { instruction ->
                InstructionItem(instruction, totalSteps)
            }
        }
    }
}

@Composable
fun InstructionItem(instruction: Direction, totalSteps: Int) {
    Column(modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                ) {
                    append("Step ${instruction.position}")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 12.sp,
                        fontWeight = if (totalSteps == instruction.position) FontWeight.Bold else null
                    )
                ) {
                    append(" / $totalSteps")
                }
            },

            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(text = instruction.text)
    }
}