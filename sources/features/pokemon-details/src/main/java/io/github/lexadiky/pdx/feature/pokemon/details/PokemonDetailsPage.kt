package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.render

@Composable
fun PokemonDetailsPage(pokemonId: String) {
    DIFeature(PokemonDetailsModule) {
        PokemonDetailsPageImpl(di.inject(pokemonId))
    }
}

@Composable
private fun PokemonDetailsPageImpl(viewModel: PokemonDetailsViewModel) {
    Text(text = "PKMN: ${viewModel.state.name.render().value}")
}