package io.github.lexadiky.pdx.feature.pokemon.list.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery

data class PokemonSearchQuery(
    val text: String = "",
    val selectedTypes: List<PokemonType> = emptyList()
) : SearchQuery<PokemonGenericListItem> {

    override fun apply(items: List<PokemonGenericListItem>): List<PokemonGenericListItem> {
        return items
            .filter { item -> item.textSearchIndex.contains(text.lowercase()) }
            .filter { item -> item.types.containsAll(selectedTypes) }
    }
}