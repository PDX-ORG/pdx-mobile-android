package io.github.lexadiky.pdx.ui.uikit.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import io.github.lexadiky.pdx.lib.blogger.BLogger
import io.github.lexadiky.pdx.lib.blogger.assert
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.ImageVectorImageResource
import io.github.lexadiky.pdx.lib.resources.image.ResImageResource
import io.github.lexadiky.pdx.lib.resources.image.UrlImageResource
import io.github.lexadiky.pdx.ui.uikit.image.rememberAsyncImagePainter
import io.github.lexadiky.pdx.ui.uikit.image.transformation.CropTransparentTransformation

@Composable
fun ImageResource.render(transformations: List<ImageTransformation> = emptyList()): Painter {
    BLogger.assert(
        message = "transformation not applicable to $this",
        condition = this !is UrlImageResource && transformations.isNotEmpty()
    )

    return when (this) {
        is ImageVectorImageResource -> rememberVectorPainter(image = this.vector)
        is UrlImageResource -> rememberAsyncImagePainter(this.url) {
            crossfade(true)
            transformations(transformations.map { transformation ->
                when (transformation) {
                    ImageTransformation.CROP_TRANSPARTENT -> CropTransparentTransformation
                }
            })
        }
        is ResImageResource -> painterResource(id = this.id)
    }
}

enum class ImageTransformation {

    CROP_TRANSPARTENT
}