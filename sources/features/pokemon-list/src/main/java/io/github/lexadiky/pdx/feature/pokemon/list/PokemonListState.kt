package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonListEntry
import io.github.lexadiky.pdx.feature.pokemon.list.entity.SearchQuery

internal data class PokemonListState(
    val items: List<PokemonListEntry> = emptyList(),
    val query: SearchQuery = SearchQuery(),
    val searchActivated: Boolean = false,
    val useAlternativeImages: Boolean = false,
) {

    val visibleItems = if (searchActivated) query.apply(items) else items
}