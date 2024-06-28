import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TrendingItemPreview() {
    AppTheme {
        Surface {
            TrendingItem(
                recipe = Recipe(
                    title = "ubique",
                    duration = "atomorum",
                    image = "malorum",
                    servings = "omnesque",
                    type = "civibus",
                    instructions = listOf(
                        Instruction(
                            step = 9625,
                            displayText = "Step1",
                            startTime = 4250,
                            endTime = 7643,
                            appliance = "penatibus",
                            temperature = 5939
                        ), Instruction(
                            step = 9625,
                            displayText = "convallis",
                            startTime = 4250,
                            endTime = 7643,
                            appliance = "penatibus",
                            temperature = 5939
                        ), Instruction(
                            step = 9625,
                            displayText = "convallis",
                            startTime = 4250,
                            endTime = 7643,
                            appliance = "penatibus",
                            temperature = 5939
                        ), Instruction(
                            step = 9625,
                            displayText = "convallis",
                            startTime = 4250,
                            endTime = 7643,
                            appliance = "penatibus",
                            temperature = 5939
                        )
                    ),
                    ingredients = listOf(
                        Ingredient(
                            title = "mi",
                            image = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.aMYg-LP-qBIn8RHS-1DDCQHaE7%26pid%3DApi%26h%3D160&f=1&ipt=faadb6a4a6a46bff0399c3db49b2019c943aad4f56a58df1ab28d28dc193f5fb&ipo=images",
                            quantity = "ubique"
                        ), Ingredient(
                            title = "mi",
                            image = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.aMYg-LP-qBIn8RHS-1DDCQHaE7%26pid%3DApi%26h%3D160&f=1&ipt=faadb6a4a6a46bff0399c3db49b2019c943aad4f56a58df1ab28d28dc193f5fb&ipo=images",
                            quantity = "ubique"
                        ), Ingredient(
                            title = "mi",
                            image = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.aMYg-LP-qBIn8RHS-1DDCQHaE7%26pid%3DApi%26h%3D160&f=1&ipt=faadb6a4a6a46bff0399c3db49b2019c943aad4f56a58df1ab28d28dc193f5fb&ipo=images",
                            quantity = "ubique"
                        ), Ingredient(
                            title = "mi",
                            image = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.aMYg-LP-qBIn8RHS-1DDCQHaE7%26pid%3DApi%26h%3D160&f=1&ipt=faadb6a4a6a46bff0399c3db49b2019c943aad4f56a58df1ab28d28dc193f5fb&ipo=images",
                            quantity = "ubique"
                        )
                    )
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.5f)
                    .height(280.dp)
            )
        }
    }

}