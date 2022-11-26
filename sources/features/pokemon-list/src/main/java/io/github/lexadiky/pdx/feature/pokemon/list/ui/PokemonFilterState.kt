package io.github.lexadiky.pdx.feature.pokemon.list.ui

import io.github.lexadiky.pdx.feature.pokemon.list.entity.SearchQuery

internal data class PokemonFilterState(
    val query: SearchQuery = SearchQuery(),
    val showTypeFilterDialog: Boolean = false
)