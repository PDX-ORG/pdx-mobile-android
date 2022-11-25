@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)

package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.animation
import io.github.lexadiky.pdx.ui.uikit.theme.sizes

@Composable
fun LargeWikiPreview(
    preTitle: String?,
    title: String,
    image: Painter,
    primaryColor: Color?,
    secondaryColor: Color?,
    tags: List<TagItem>,
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s1),
                ) {
                    if (preTitle != null) {
                        Text(
                            text = preTitle,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.alignByBaseline()
                        )
                    }
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.alignByBaseline()
                    )
                }
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
                    .align(Alignment.CenterEnd)
            ) { self ->
                Image(
                    painter = self,
                    contentDescription = null,
                )
            }
        }
    }
}

data class TagItem(val title: StringResource, val color: ColorResource)

@Composable
private fun TagStrip(tags: List<TagItem>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s05),
        modifier = modifier
    ) {
        for (tag in tags) {
            ElevatedSuggestionChip(
                label = { Text(text = tag.title.render().value) },
                colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                    containerColor = tag.color.render()
                ),
                enabled = true,
                onClick = {}
            )
        }
    }
}
