package io.github.lexadiky.pdx.feature.pokemon.list.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType

internal data class SearchQuery(
    val text: String = "",
    val selectedTypes: List<PokemonType> = emptyList()
) {

    fun apply(items: List<PokemonListEntry>): List<PokemonListEntry> {
        return items
            .filter { item -> item.textSearchIndex.contains(text.lowercase()) }
            .filter { item -> item.types.containsAll(selectedTypes) }
    }
}