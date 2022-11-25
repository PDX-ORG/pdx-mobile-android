@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)

package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import io.github.lexadiky.pdx.ui.uikit.theme.animation
import io.github.lexadiky.pdx.ui.uikit.theme.sizes

@Composable
fun LargeWikiPreview(
    title: String,
    image: Painter,
    primaryColor: Color?,
    secondaryColor: Color?,
    tags: List<String>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.sizes.s2)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TagStrip(tags = tags)
            }

            AnimatedContent(
                targetState = image,
                transitionSpec = {
                    scaleIn(MaterialTheme.animation.linearSlow())
                        .with(scaleOut(MaterialTheme.animation.linearSlow()))
                },
                modifier = Modifier
                    .size(MaterialTheme.sizes.sN(8))
                    .align(Alignment.BottomEnd)
            ) { self ->
                Image(
                    painter = self,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun TagStrip(tags: List<String>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s05),
        modifier = modifier
    ) {
        for (tag in tags) {
            ElevatedSuggestionChip(
                label = { Text(text = tag) },
                enabled = true,
                onClick = {}
            )
        }
    }
}
