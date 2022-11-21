package io.github.lexadiky.pdx.ui.uikit.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import io.github.lexadiky.pdx.lib.blogger.BLogger
import io.github.lexadiky.pdx.lib.blogger.error
import io.github.lexadiky.pdx.lib.blogger.verbose

@Composable
fun rememberAsyncImagePainter(model: Any, builder: ImageRequest.Builder.() -> Unit = {}): Painter {
    val context = LocalContext.current

    val request = remember(model, builder) {
        ImageRequest.Builder(context)
            .data(model)
            .apply(builder)
            .build()
    }

    return coil.compose.rememberAsyncImagePainter(
        model = request,
        imageLoader = LocalImageLoader.current,
        filterQuality = FilterQuality.None,
        onState = { state ->
            when(state) {
                is AsyncImagePainter.State.Error ->
                    BLogger.tag("rememberAsyncImagePainter")
                        .error("can't load image: $model", state.result.throwable)
                else ->
                    BLogger.tag("rememberAsyncImagePainter")
                        .verbose("image loading status ${state::class}")
            }
        }
    )
}