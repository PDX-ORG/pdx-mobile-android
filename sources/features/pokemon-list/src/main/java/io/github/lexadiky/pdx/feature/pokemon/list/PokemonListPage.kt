@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)

package io.github.lexadiky.pdx.feature.pokemon.list

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonListEntry
import io.github.lexadiky.pdx.feature.pokemon.list.entity.SearchQuery
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilter
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilterViewModel
import io.github.lexadiky.pdx.feature.pokemonlist.R
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.widget.FastScrollWheel
import io.github.lexadiky.pdx.ui.uikit.widget.LargeWikiPreview
import io.github.lexadiky.pdx.ui.uikit.widget.TagItem
import io.github.lexadiky.pdx.ui.uikit.widget.ToggleableFab

@Composable
fun PokemonListPage() {
    DIFeature(PokemonListModule) {
        PokemonListPageImpl(di.inject())
    }
}

private const val SEARCH_QUERY_ITEM_ID = "search_query_item"

@Composable
private fun PokemonListPageImpl(viewModel: PokemonListViewModel) {
    val filterViewModel: PokemonFilterViewModel = di.inject({ newQuery: SearchQuery ->
        viewModel.updateQuery(newQuery)
    })

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
                    if (viewModel.state.searchActivated) {
                        item(SEARCH_QUERY_ITEM_ID) {
                            PokemonFilter(filterViewModel)
                        }
                    }
                    items(viewModel.state.visibleItems, { entry -> entry.id }) { entry ->
                        LargeWikiPreview(
                            preTitle = entry.nationalId.render().value,
                            title = entry.name.render().value,
                            image = pokemonImagePainter(
                                entry,
                                viewModel.state.useAlternativeImages
                            ),
                            primaryColor = Color.Red,
                            secondaryColor = Color.Yellow,
                            tags = entry.types.map {
                                TagItem(
                                    it.toStringResource(),
                                    it.toColorResource()
                                )
                            },
                            onClick = { /*TODO*/ },
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
            SearchModeToggleFab(viewModel.state) {
                viewModel.toggleSearchMode()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun SearchModeToggleFab(state: PokemonListState, onToggle: () -> Unit) {
    ToggleableFab(
        state.searchActivated,
        toggled = { Icon(Icons.Default.Close, null) },
        untoggled = { Icon(Icons.Default.Search, null) },
        onToggle = onToggle
    )
}

@Composable
private fun pokemonImagePainter(entry: PokemonListEntry, useAlternativeImages: Boolean): Painter {
    return if (useAlternativeImages) {
        entry.alternativeImage.render(listOf(ImageTransformation.CROP_TRANSPARTENT))
    } else {
        entry.image.render(listOf(ImageTransformation.CROP_TRANSPARTENT))
    }
}