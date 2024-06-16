package org.example.recipes

import AppTheme
import Instruction
import Instructions
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun InstructionItemPreview() {
    AppTheme {
        Surface {
            Instructions(
                instructions = listOf(
                    Instruction(
                        step = 1,
                        displayText = "Step1",
                        startTime = 1982,
                        endTime = 1309,
                        appliance = "consectetur",
                        temperature = 3957

                    ), Instruction(
                        step = 2,
                        displayText = "Step2",
                        startTime = 6051,
                        endTime = 1759,
                        appliance = "quisque",
                        temperature = 2375
                    )
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}