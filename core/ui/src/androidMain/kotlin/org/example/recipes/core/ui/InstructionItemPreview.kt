package org.example.recipes.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.recipes.core.model.Direction


@Preview
@Composable
fun InstructionItemPreview() {
    Surface {
        Instructions(
            instructions = listOf(
                Direction(
                    id = 2297,
                    position = 1390,
                    startTime = 8367,
                    endTime = 6332,
                    appliance = null,
                    temperature = null,
                    text = "option"
                ),
                Direction(
                    id = 2297,
                    position = 1390,
                    startTime = 8367,
                    endTime = 6332,
                    appliance = null,
                    temperature = null,
                    text = "option"
                ),
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
