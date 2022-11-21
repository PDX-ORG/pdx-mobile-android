package io.github.lexadiky.pdx.ui.uikit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object PdxSizes {

    val s05 = 4.dp
    val s1 = 8.dp
    val s2 = 16.dp
    val s4 = 32.dp

    fun sN(multiplicator: Int): Dp = s1 * multiplicator
}

val MaterialTheme.sizes: PdxSizes get() = PdxSizes