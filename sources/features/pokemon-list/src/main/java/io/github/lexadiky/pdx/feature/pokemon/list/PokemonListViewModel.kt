package io.github.lexadiky.pdx.feature.pokemon.list

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
import io.github.lexadiky.pdx.ui.uikit.util.ShakeDetector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

internal class PokemonListViewModel(
    private val getPokemon: GetPokemonUseCase,
    private val shakeDetector: ShakeDetector
) : ViewModel() {

    var state by mutableStateOf(PokemonListState())
        private set

    init {
        viewModelScope.launch {
            val result = getPokemon() as Either.Right
            val entries = result.value.map { pokemon ->
                PokemonListEntry(
                    id = pokemon.name,
                    nationalId = StringResource.from(R.string.pokemon_list_national_id_template)
                        .format(pokemon.nationalDexNumber),
                    name = StringResource.from(pokemon.localNames[PokemonLanguage.ENGLISH]!!),
                    primaryColor = ColorResource.from(Color.Yellow),
                    secondaryColor = ColorResource.from(Color.Red),
                    image = pokemon.normalSprite?.let { ImageResource.from(it) }
                        ?: ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball),
                    alternativeImage = pokemon.shinySprite?.let { ImageResource.from(it) }
                        ?: ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball),
                    types = pokemon.types
                )
            }
            state = state.copy(items = entries)
        }

        viewModelScope.launch {
            shakeDetector.events.debounce(ALTERNATIVE_IMAGE_CHANGE_DEBOUNCE).collectLatest {
               state = state.copy(useAlternativeImages = !state.useAlternativeImages)
            }
        }
    }

    companion object {

        private val ALTERNATIVE_IMAGE_CHANGE_DEBOUNCE = 1.seconds
    }
}