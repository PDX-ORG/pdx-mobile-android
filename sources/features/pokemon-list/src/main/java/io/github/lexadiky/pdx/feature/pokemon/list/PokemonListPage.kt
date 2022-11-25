package io.github.lexadiky.pdx.feature.pokemon.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonListEntry
import io.github.lexadiky.pdx.ui.uikit.widget.LargeWikiPreview
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.widget.TagItem

@Composable
fun PokemonListPage() {
    DIFeature(PokemonListModule) {
        PokemonListPageImpl(di.inject())
    }
}

@Composable
private fun PokemonListPageImpl(viewModel: PokemonListViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2),
        contentPadding = PaddingValues(MaterialTheme.sizes.s2)
    ) {
        items(viewModel.state.items) { entry ->
            LargeWikiPreview(
                preTitle = entry.nationalId.render().value,
                title = entry.name.render().value,
                image = pokemonImagePainter(entry, viewModel.state.useAlternativeImages),
                primaryColor = Color.Red,
                secondaryColor = Color.Yellow,
                tags = entry.types.map { TagItem(it.toStringResource(), it.toColorResource()) },
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun pokemonImagePainter(entry: PokemonListEntry, useAlternativeImages: Boolean): Painter {
    return if (useAlternativeImages) {
        entry.alternativeImage.render(listOf(ImageTransformation.CROP_TRANSPARTENT))
    } else {
        entry.image.render(listOf(ImageTransformation.CROP_TRANSPARTENT))
    }
}