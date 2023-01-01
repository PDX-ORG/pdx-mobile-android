package io.github.lexadiky.pdx.ui.uikit.util

import androidx.compose.ui.graphics.Color

fun Color.saturation(modifier: Float): Color {
    return copy(
        red = ensureColorValueRange(red * modifier),
        green = ensureColorValueRange(green * modifier),
        blue = ensureColorValueRange(blue * modifier)
    )
}

private fun ensureColorValueRange(value: Float): Float =
    value.coerceIn(0f, 1f)