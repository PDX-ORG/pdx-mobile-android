package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.util.saturation

object PillChipDefaults {

    fun trailColor(color: Color): Color =
        color.saturation(1.2f)
}

@Composable
fun PillChip(
    label: @Composable () -> Unit,
    labelColor: Color,
    trail: @Composable () -> Unit,
    trailColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    val textStyle = LocalTextStyle.current.copy(color = textColor)
    CompositionLocalProvider(LocalTextStyle provides textStyle) {
        Surface(
            shape = RoundedCornerShape(MaterialTheme.sizes.s1),
            modifier = modifier
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .background(labelColor)
                        .padding(MaterialTheme.sizes.s1)
                ) {
                    label()
                }
                Box(
                    modifier = Modifier
                        .background(trailColor)
                        .padding(MaterialTheme.sizes.s1)
                ) {
                    trail()
                }
            }
        }
    }
}
