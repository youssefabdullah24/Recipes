import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.cupertino.CupertinoText


data class Instruction(
    val step: Int,
    val displayText: String,
    val startTime: Long,
    val endTime: Long,
    val appliance: String,
    val temperature: Int
)

@Composable
fun Instructions(instructions: List<Instruction>,
                 modifier: Modifier = Modifier) {
    val totalSteps = instructions.size
    Column(modifier = modifier) {
        CupertinoText(
            text = buildAnnotatedString {
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                ) {
                    append("Directions")
                }
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
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
fun InstructionItem(instruction: Instruction, totalSteps: Int) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        CupertinoText(
            text = buildAnnotatedString {
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                ) {
                    append("Step ${instruction.step}")
                }
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        fontSize = 12.sp
                    )
                ) {
                    append(" / $totalSteps")
                }
            },

            modifier = Modifier.padding(bottom = 4.dp)
        )
        CupertinoText(text = instruction.displayText)
    }
}