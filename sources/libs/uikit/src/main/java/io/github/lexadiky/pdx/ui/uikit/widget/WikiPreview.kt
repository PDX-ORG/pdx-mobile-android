@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.github.lexadiky.pdx.ui.uikit.theme.sizes

@Composable
fun WikiPreview(
    title: String,
    subtitle: String?,
    image: Painter,
    primaryColor: Color?,
    secondaryColor: Color?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorStripList = remember(primaryColor, secondaryColor) {
        listOfNotNull(primaryColor, secondaryColor)
    }

    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.sizes.s2)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s1),
                modifier = Modifier
                    .padding(bottom = MaterialTheme.sizes.s2)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomEnd)
            )

            if (colorStripList.isNotEmpty()) {
                ColorStrip(
                    colors = colorStripList,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }
        }


    }
}

@Composable
private fun ColorStrip(colors: List<Color>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s05),
        modifier = modifier
    ) {
        for (color in colors) {
            Canvas(modifier = Modifier.size(MaterialTheme.sizes.s2)) {
                drawCircle(color)
            }
        }
    }
}
