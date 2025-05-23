import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.alexzhirkevich.cupertino.adaptive.Theme


expect fun determineTheme() : Theme
@Composable
expect fun accentColor() : Color
