package io.github.lexadiky.pdx.feature.typechart.search

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelations
import io.github.lexadiky.pdx.feature.typechart.R
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonSuggester
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonTypeSearchItem
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.util.UikitStringFormatter
import kotlinx.coroutines.launch

internal class TypeSearchViewModel(
    private val getPokemonPreview: GetPokemonPreviewUseCase,
    private val getPokemonDamageRelations: GetPokemonTypeDamageRelations,
    private val suggester: PokemonSuggester,
    private val context: Context
) : ViewModel() {

    var state by mutableStateOf(TypeSearchState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getPokemonPreview()) {
                is Either.Left -> state.copy(error = UIError.default())
                is Either.Right -> state.copy(allPokemon = data.value.toUi())
            }
        }
        viewModelScope.launch {
            state = when (val data = getPokemonDamageRelations()) {
                is Either.Left -> state.copy(error = UIError.default())
                is Either.Right -> state.copy(
                    damageRelationsSubState = state.damageRelationsSubState.copy(
                        damageRelations = data.value
                    )
                )
            }
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    fun updateSearchQuery(text: String) {
        viewModelScope.launch {
            val suggestion = suggester.suggest(state.allPokemon, text)
            state = state.copy(
                searchQuery = text,
                suggestedPokemon = suggestion.hints,
            )
            state = if (suggestion.hints.isEmpty() || suggestion.hints.size == 1) {
                state.setSelectedPokemon(suggestion.mostLikely)
            } else {
                state.setSelectedPokemon(null)
            }
        }
    }

    fun selectHint(pokemon: PokemonTypeSearchItem) {
        updateSearchQuery(pokemon.name.render(context))
    }

    private fun List<PokemonPreview>.toUi(): List<PokemonTypeSearchItem> {
        return map { preview ->
            PokemonTypeSearchItem(
                name = StringResource.from(preview.localNames[PokemonLanguage.ENGLISH]!!),
                searchQueryIndex = preview.simpleSearchIndex,
                image = preview.normalSprite?.let { ImageResource.from(it) }
                    ?: ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball),
                nationalId = UikitStringFormatter.nationalId(preview.nationalDexNumber),
                types = preview.types
            )
        }
    }
}