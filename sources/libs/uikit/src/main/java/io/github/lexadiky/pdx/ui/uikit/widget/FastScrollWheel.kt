package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.util.delayState
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

private const val ITEM_CHUNK = 100

@Composable
fun <T> FastScrollWheel(
    items: List<T>,
    columnState: LazyListState,
    modifier: Modifier = Modifier,
) {
    val localState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isScrollInProgressRecently by delayState(1.seconds) {
        columnState.isScrollInProgress || localState.isScrollInProgress
    }

    AnimatedVisibility(
        visible = isScrollInProgressRecently,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(
            state = localState,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2),
            modifier = Modifier
                .padding(MaterialTheme.sizes.s1)
                .clip(MaterialTheme.shapes.circular)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                .fillMaxHeight(0.5f)
        ) {
            items(items.chunked(ITEM_CHUNK).indices.toList()) { idx ->
                Text(
                    text = idx.toString(),
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.circular)
                        .clickable {
                            scope.launch {
                                val offset = columnState.layoutInfo.totalItemsCount - items.size
                                columnState.scrollToItem(offset + idx * ITEM_CHUNK)
                            }
                        }
                        .background(MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.75f),)
                        .padding(MaterialTheme.sizes.s1)
                )
            }
        }
    }
}
