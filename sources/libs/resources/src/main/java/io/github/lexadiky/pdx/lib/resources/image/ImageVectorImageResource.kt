package io.github.lexadiky.pdx.lib.resources.image

import androidx.compose.ui.graphics.vector.ImageVector

class ImageVectorImageResource(val vector: ImageVector) : ImageResource

fun ImageResource.Companion.from(vector: ImageVector): ImageResource = ImageVectorImageResource(vector)