package io.github.lexadiky.pdx.ui.uikit.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

object PdxColors {

    val externalBrands = Brands

    object Brands {

        val reddit = Color(0xFFFF6314)
    }
}

val ColorScheme.pdx get() = PdxColors
