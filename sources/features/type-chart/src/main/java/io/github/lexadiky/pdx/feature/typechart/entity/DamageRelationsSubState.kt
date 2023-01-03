package io.github.lexadiky.pdx.feature.typechart.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelationTable
import io.github.lexadiky.pdx.domain.pokemon.util.times

data class DamageRelationsSubState(
    val damageRelations:  Map<PokemonType, PokemonTypeDamageRelation> = emptyMap(),
    val selectedTypes: List<PokemonType> = emptyList()
) {

    private val selectedRelationTable: PokemonTypeDamageRelationTable? = selectedTypes.takeIf { it.isNotEmpty() }
        ?.map { damageRelations[it]?.asTable() }
        ?.filterNotNull()
        ?.takeIf { it.isNotEmpty() }
        ?.reduce { a, b -> a * b }

    val attackDamageRelationTable: List<TypeDamageValue> = formatAndSortDamageRelations(selectedRelationTable?.to)
    val defenceDamageRelationTable: List<TypeDamageValue> = formatAndSortDamageRelations(selectedRelationTable?.from)

    private fun formatAndSortDamageRelations(table: Map<PokemonType, Float>?): List<TypeDamageValue> {
        if (table == null) {
            return emptyList()
        }
        return table.map { (key, value) -> TypeDamageValue(key, value) }
            .sortedBy { -it.value }
    }
}