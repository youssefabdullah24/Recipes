import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private typealias AndroidColorScheme = ColorScheme
private typealias IosColorScheme = com.slapps.cupertino.theme.ColorScheme

val androidLightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

val androidDarkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val iosLightScheme = com.slapps.cupertino.theme.lightColorScheme(
    accent = primaryLight,                         // e.g., your main brand color
    label = onBackgroundLight,                     // e.g., Color(0xFFE6E1E5)
    secondaryLabel = onSurfaceVariantLight,          // e.g., Color(0xFFCAC4CF)
    tertiaryLabel = onSurfaceVariantLight.copy(alpha = 0.7f), // Make it more subtle
    quaternaryLabel = onSurfaceVariantLight.copy(alpha = 0.4f),// Even more subtle

    systemFill = onSurfaceLight.copy(alpha = 0.15f),
    secondarySystemFill = onSurfaceLight.copy(alpha = 0.1f),
    tertiarySystemFill = onSurfaceLight.copy(alpha = 0.08f),
    quaternarySystemFill = onSurfaceLight.copy(alpha = 0.05f),

    placeholderText = onSurfaceVariantLight.copy(alpha = 0.7f), // Same as tertiaryLabel or specific

    separator = outlineVariantLight,                 // e.g., Color(0xFF49454F) or onSurfaceDark.copy(alpha=0.2f)
    opaqueSeparator = outlineLight,                  // e.g., Color(0xFF938F99)

    link = primaryLight,                           // Or a specific link blue

    systemBackground = backgroundLight,              // e.g., Color(0xFF1C1B1F)
    secondarySystemBackground = surfaceContainerLowLight, // e.g., Color(0xFF242327) -> slightly lighter than background
    tertiarySystemBackground = surfaceContainerLight,   // e.g., Color(0xFF2B2A2E) -> even lighter

    systemGroupedBackground = backgroundLight,         // Often same as systemBackground or very close
    // or surfaceContainerLowestDark if you want a subtle difference
    secondarySystemGroupedBackground = surfaceLight,     // Background for cells within grouped content
    tertiarySystemGroupedBackground = surfaceContainerLight //
)

private val iosDarkScheme = com.slapps.cupertino.theme.darkColorScheme(
  /*  accent = primaryDark,                         // e.g., your main brand color
    label = onBackgroundDark,                     // e.g., Color(0xFFE6E1E5)
    secondaryLabel = onSurfaceVariantDark,          // e.g., Color(0xFFCAC4CF)
    tertiaryLabel = onSurfaceVariantDark.copy(alpha = 0.7f), // Make it more subtle
    quaternaryLabel = onSurfaceVariantDark.copy(alpha = 0.4f),// Even more subtle

    systemFill = onSurfaceDark.copy(alpha = 0.15f),
    secondarySystemFill = onSurfaceDark.copy(alpha = 0.1f),
    tertiarySystemFill = onSurfaceDark.copy(alpha = 0.08f),
    quaternarySystemFill = onSurfaceDark.copy(alpha = 0.05f),

    placeholderText = onSurfaceVariantDark.copy(alpha = 0.7f), // Same as tertiaryLabel or specific

    separator = outlineVariantDark,                 // e.g., Color(0xFF49454F) or onSurfaceDark.copy(alpha=0.2f)
    opaqueSeparator = outlineDark,                  // e.g., Color(0xFF938F99)

    link = primaryDark,                           // Or a specific link blue

    systemBackground = backgroundDark,              // e.g., Color(0xFF1C1B1F)
    secondarySystemBackground = surfaceContainerLowDark, // e.g., Color(0xFF242327) -> slightly lighter than background
    tertiarySystemBackground = surfaceContainerDark,   // e.g., Color(0xFF2B2A2E) -> even lighter

    systemGroupedBackground = backgroundDark,         // Often same as systemBackground or very close
    // or surfaceContainerLowestDark if you want a subtle difference
    secondarySystemGroupedBackground = surfaceDark,     // Background for cells within grouped content
    tertiarySystemGroupedBackground = surfaceContainerDark */
)

@Composable
fun androidColorScheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
): AndroidColorScheme {
    return when {
        darkTheme -> androidDarkScheme
        else -> androidLightScheme
    }
}


@Composable
fun iosColorScheme(
    darkTheme: Boolean = isSystemInDarkTheme()
): IosColorScheme {
    return when {
        darkTheme -> iosDarkScheme
        else -> iosLightScheme
    }
}