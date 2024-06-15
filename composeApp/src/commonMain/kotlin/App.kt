

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        IngredientItem(
             modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            ingredient = Ingredient(
                title = "Orange", image = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.__bAQzvCB_7o1DCTfv8RQwHaHa%26pid%3DApi%26h%3D160&f=1&ipt=ad1fd73c2dae264b0ec6dcb4981df28fe094fc548bad2fd9296a05050ba6cb97&ipo=images", quantity = "1 pcs"

            )
        )
    }
}