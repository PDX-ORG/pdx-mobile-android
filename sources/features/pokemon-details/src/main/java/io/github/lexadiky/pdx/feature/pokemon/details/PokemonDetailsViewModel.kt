package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.lexadiky.pdx.lib.resources.string.*

internal class PokemonDetailsViewModel(private val pokemonId: String) : ViewModel() {

    var state: PokemonDetailsState by mutableStateOf(PokemonDetailsState(name = StringResource.from(pokemonId)))
        private set
}