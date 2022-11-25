package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonListEntry

internal data class PokemonListState(
    val items: List<PokemonListEntry> = emptyList(),
    val useAlternativeImages: Boolean = false
)