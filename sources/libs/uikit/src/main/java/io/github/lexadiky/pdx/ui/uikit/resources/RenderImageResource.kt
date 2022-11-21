package io.github.lexadiky.pdx.ui.uikit.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.ImageVectorImageResource
import io.github.lexadiky.pdx.lib.resources.image.UrlImageResource
import io.github.lexadiky.pdx.ui.uikit.image.rememberAsyncImagePainter

@Composable
fun ImageResource.render(): Painter {
    return when (this) {
        is ImageVectorImageResource -> rememberVectorPainter(image = this.vector)
        is UrlImageResource -> rememberAsyncImagePainter(this.url)
    }
}
