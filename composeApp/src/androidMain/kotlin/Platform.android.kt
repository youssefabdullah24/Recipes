import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.alexzhirkevich.cupertino.adaptive.Theme

actual fun determineTheme(): Theme = Theme.Material3

@Composable
actual fun accentColor(): Color = MaterialTheme.colorScheme.primary