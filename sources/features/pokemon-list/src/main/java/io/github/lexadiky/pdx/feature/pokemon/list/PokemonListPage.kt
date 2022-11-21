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
import io.github.lexadiky.pdx.LargeWikiPreview
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes

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
                title = entry.name.render().value,
                image = entry.image.render(
                    listOf(ImageTransformation.CROP_TRANSPARTENT)
                ),
                primaryColor = Color.Red,
                secondaryColor = Color.Yellow,
                tags = listOf("Fire", "Water"),
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}