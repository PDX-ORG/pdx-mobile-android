package io.github.lexadiky.pdx.feature.pokemon.list

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonListEntry
import io.github.lexadiky.pdx.feature.pokemon.list.entity.domain.PokemonLanguage
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.GetPokemonUseCase
import io.github.lexadiky.pdx.feature.pokemonlist.R
import io.github.lexadiky.pdx.lib.resources.color.*
import io.github.lexadiky.pdx.lib.resources.image.*
import io.github.lexadiky.pdx.lib.resources.string.*
import kotlinx.coroutines.launch

internal class PokemonListViewModel(private val getPokemon: GetPokemonUseCase) : ViewModel() {

    var state by mutableStateOf(PokemonListState())
        private set

    init {
        viewModelScope.launch {
            val result = getPokemon() as Either.Right
            val entries = result.value.map { pokemon ->
                PokemonListEntry(
                    id = pokemon.name,
                    name = StringResource.from(pokemon.localNames[PokemonLanguage.ENGLISH]!!),
                    primaryColor = ColorResource.from(Color.Yellow),
                    secondaryColor = ColorResource.from(Color.Red),
                    image = pokemon.normalSprite?.let { ImageResource.from(it) }
                        ?: ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball)
                )
            }
            state = state.copy(items = entries)
        }
    }
}