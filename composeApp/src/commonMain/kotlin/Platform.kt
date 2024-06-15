import io.github.alexzhirkevich.cupertino.adaptive.Theme
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun determineTheme() : Theme