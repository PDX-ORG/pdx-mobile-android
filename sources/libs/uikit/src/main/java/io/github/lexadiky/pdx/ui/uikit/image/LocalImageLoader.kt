package io.github.lexadiky.pdx.ui.uikit.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader

val LocalImageLoader = compositionLocalOf<ImageLoader> {
    throw IllegalStateException("${ImageLoader::class} not in composition context")
}

@Composable
fun ProvideLocalImageLoader(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val imageLoader = remember(context) {
        ImageLoader(context)
    }
    CompositionLocalProvider(LocalImageLoader provides imageLoader) {
        content()
    }
}