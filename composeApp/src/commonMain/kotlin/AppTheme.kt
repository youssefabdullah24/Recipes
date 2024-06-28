
import androidx.compose.runtime.Composable
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTheme
import io.github.alexzhirkevich.cupertino.adaptive.CupertinoThemeSpec
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.MaterialThemeSpec
import io.github.alexzhirkevich.cupertino.adaptive.Theme

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun AppTheme(
    theme: Theme = determineTheme(),
    content: @Composable () -> Unit
) {
   /* {
        MaterialTheme(
            colorScheme = if (useDarkTheme) {
                androidx.compose.material3.darkColorScheme()
            } else {
                androidx.compose.material3.lightColorScheme()
            },
            content = it
        )
    }
    {
        CupertinoTheme(
            colorScheme = if (useDarkTheme) {
                darkColorScheme()
            } else {
                lightColorScheme()
            },
            content = it
        )

    }*/
    AdaptiveTheme(
        target = theme,
        material = MaterialThemeSpec.Default(),
        cupertino = CupertinoThemeSpec.Default(),
        content = content
    )
}