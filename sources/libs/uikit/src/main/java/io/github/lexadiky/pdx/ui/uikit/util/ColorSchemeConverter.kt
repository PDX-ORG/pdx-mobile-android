package io.github.lexadiky.pdx.ui.uikit.util

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.google.android.material.color.utilities.Scheme

fun Scheme.toColorScheme(): ColorScheme = ColorScheme(
    primary = this.primary.toComposeColor(),
    onPrimary = this.onPrimary.toComposeColor(),
    primaryContainer = this.primaryContainer.toComposeColor(),
    onPrimaryContainer = this.onPrimaryContainer.toComposeColor(),
    inversePrimary = this.inversePrimary.toComposeColor(),
    secondary = this.secondary.toComposeColor(),
    onSecondary = this.onSecondary.toComposeColor(),
    secondaryContainer = this.secondaryContainer.toComposeColor(),
    onSecondaryContainer = this.onSecondaryContainer.toComposeColor(),
    tertiary = this.tertiary.toComposeColor(),
    onTertiary = this.onTertiary.toComposeColor(),
    tertiaryContainer = this.tertiaryContainer.toComposeColor(),
    onTertiaryContainer = this.onTertiaryContainer.toComposeColor(),
    background = this.background.toComposeColor(),
    onBackground = this.onBackground.toComposeColor(),
    surface = this.surface.toComposeColor(),
    onSurface = this.onSurface.toComposeColor(),
    surfaceVariant = this.surfaceVariant.toComposeColor(),
    onSurfaceVariant = this.onSurfaceVariant.toComposeColor(),
    surfaceTint = this.surfaceVariant.toComposeColor(),
    inverseSurface = this.inverseSurface.toComposeColor(),
    inverseOnSurface = this.inverseOnSurface.toComposeColor(),
    error = this.error.toComposeColor(),
    onError = this.onError.toComposeColor(),
    errorContainer = this.errorContainer.toComposeColor(),
    onErrorContainer = this.onErrorContainer.toComposeColor(),
    outline = this.outline.toComposeColor(),
    outlineVariant =  this.outlineVariant.toComposeColor(),
    scrim = this.scrim.toComposeColor()
)

private fun Int.toComposeColor(): Color {
    return Color(this)
}