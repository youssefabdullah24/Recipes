import androidx.compose.runtime.Composable
import com.slapps.cupertino.adaptive.AdaptiveTheme
import com.slapps.cupertino.adaptive.CupertinoThemeSpec
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import com.slapps.cupertino.adaptive.MaterialThemeSpec
import com.slapps.cupertino.adaptive.Theme


@OptIn(ExperimentalAdaptiveApi::class)
@Composable
internal fun AppTheme(
    theme: Theme = determineTheme(),
    content: @Composable () -> Unit
) {
    AdaptiveTheme(
        target = theme,
        material = MaterialThemeSpec.Default(colorScheme = androidColorScheme()),
        cupertino = CupertinoThemeSpec.Default(colorScheme = iosColorScheme()),
        content = content
    )
}