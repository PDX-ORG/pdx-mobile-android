package io.github.lexadiky.pdx.lib.resources.color

import androidx.compose.ui.graphics.Color

class LiteralColorResource(val color: Color) : ColorResource

fun ColorResource.Companion.from(color: Color): ColorResource =
    LiteralColorResource(color)