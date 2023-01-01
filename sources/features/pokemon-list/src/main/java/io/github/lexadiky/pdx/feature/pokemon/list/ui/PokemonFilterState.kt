package io.github.lexadiky.pdx.feature.pokemon.list.ui

import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonSearchQuery

internal data class PokemonFilterState(
    val query: PokemonSearchQuery = PokemonSearchQuery(),
    val showTypeFilterDialog: Boolean = false
)