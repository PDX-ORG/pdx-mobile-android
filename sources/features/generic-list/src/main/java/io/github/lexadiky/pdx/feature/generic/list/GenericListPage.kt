@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.generic.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.widget.FastScrollWheel
import io.github.lexadiky.pdx.ui.uikit.widget.LargeWikiPreview
import io.github.lexadiky.pdx.ui.uikit.widget.TagItem
import io.github.lexadiky.pdx.ui.uikit.widget.ToggleableFab

typealias FilterBlock<T> =  @Composable (queryCallback: (SearchQuery<T>) -> Unit) -> Unit

@Composable
fun <T : GenericListItem> GenericListPage(
    filterBlock: FilterBlock<T>?,
    viewModel: GenericListViewModel<T>
) {
    GenericListPageImpl(
        viewModel = viewModel,
        filterBlock = filterBlock
    )
}

private const val SEARCH_QUERY_ITEM_ID = "search_query_item"

@Composable
private fun <T : GenericListItem> GenericListPageImpl(
    filterBlock: FilterBlock<T>?,
    viewModel: GenericListViewModel<T>
) {
    LaunchedEffect(filterBlock == null) {
        viewModel.setSearchAvailable(filterBlock != null)
    }

    ErrorDialog(error = viewModel.state.uiError) {
        viewModel.hideError()
    }

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                val columnState = rememberLazyListState()
                LaunchedEffect(viewModel.state.searchActivated) {
                    if (viewModel.state.searchActivated) {
                        columnState.scrollToItem(0)
                    }
                }
                LazyColumn(
                    state = columnState,
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2),
                    contentPadding = PaddingValues(MaterialTheme.sizes.s2)
                ) {
                    if (viewModel.state.searchActivated && viewModel.state.searchAvailable) {
                        item(SEARCH_QUERY_ITEM_ID) {
                            filterBlock?.invoke { query ->
                                viewModel.updateQuery(query)
                            }
                        }
                    }
                    items(viewModel.state.visibleItems, { entry -> entry.id }) { entry ->
                        LargeWikiPreview(
                            preTitle = entry.note.render().value,
                            title = entry.title.render().value,
                            image = genericListItemPreviewPainter(
                                item = entry,
                                useAlternativeImages = viewModel.state.useAlternativeImages
                            ),
                            primaryColor = Color.Red,
                            secondaryColor = Color.Yellow,
                            tags = entry.tags.map { TagItem(it.text, it.color) },
                            onClick = { viewModel.openDetails(entry) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                FastScrollWheel(
                    items = viewModel.state.visibleItems,
                    columnState = columnState,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        },
        floatingActionButton = {
            if (viewModel.state.searchAvailable) {
                SearchModeToggleFab(viewModel.state) {
                    viewModel.toggleSearchMode()
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun SearchModeToggleFab(state: GenericListState<*>, onToggle: () -> Unit) {
    ToggleableFab(
        state.searchActivated,
        toggled = { Icon(Icons.Default.Close, null) },
        untoggled = { Icon(Icons.Default.Search, null) },
        onToggle = onToggle
    )
}

@Composable
private fun genericListItemPreviewPainter(item: GenericListItem, useAlternativeImages: Boolean): Painter {
    return if (!useAlternativeImages && item.secondaryImage != null) {
        item.secondaryImage!!.render(listOf(ImageTransformation.CROP_TRANSPARTENT))
    } else {
        item.primaryImage.render(listOf(ImageTransformation.CROP_TRANSPARTENT))
    }
}
