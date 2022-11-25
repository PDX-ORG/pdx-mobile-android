package io.github.lexadiky.pdx.ui.uikit.theme

import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme

object PdxAnimation {

    fun <T> linearSlow() = tween<T>(1000)
}

val MaterialTheme.animation get() = PdxAnimation