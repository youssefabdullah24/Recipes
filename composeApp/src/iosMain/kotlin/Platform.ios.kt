import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.alexzhirkevich.cupertino.adaptive.Theme
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

actual fun determineTheme(): Theme = Theme.Cupertino

@Composable
actual fun accentColor(): Color = CupertinoTheme.colorScheme.accent
