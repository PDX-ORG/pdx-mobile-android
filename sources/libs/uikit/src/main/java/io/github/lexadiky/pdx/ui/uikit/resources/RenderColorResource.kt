package io.github.lexadiky.pdx.ui.uikit.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import io.github.lexadiky.pdx.lib.resources.color.LiteralColorResource

@Composable
fun ColorResource.render(): Color {
    return when (this) {
        is LiteralColorResource -> color
    }
}
