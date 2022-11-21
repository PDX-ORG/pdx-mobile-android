package io.github.lexadiky.pdx.ui.uikit.image

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import io.github.lexadiky.pdx.lib.uikit.R

object CustomIcons {

    val Reddit: Painter @Composable get() = painterResource(id = R.drawable.uikit_ic_reddit)
}


val Icons.Filled.Custom: CustomIcons get() = CustomIcons
