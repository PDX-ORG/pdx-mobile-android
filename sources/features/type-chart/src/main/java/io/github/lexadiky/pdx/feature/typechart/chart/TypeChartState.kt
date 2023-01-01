package io.github.lexadiky.pdx.feature.typechart.chart

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelationTable
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.util.times
import io.github.lexadiky.pdx.feature.typechart.entity.TypeDamageValue
import java.util.Comparator

data class TypeChartState(
    val selectedTypes: List<PokemonType> = emptyList(),
    val damageRelations:  Map<PokemonType, PokemonTypeDamageRelation> = emptyMap(),
) {

    val allTypes: List<PokemonType> = PokemonType.values().toList()
    val selectedRelationTable: PokemonTypeDamageRelationTable? = selectedTypes.takeIf { it.isNotEmpty() }
        ?.map { damageRelations[it]!!.asTable() }
        ?.reduce { a, b -> a * b }

    val attackDamageRelationTable: List<TypeDamageValue> = formatAndSortDamageRelations(selectedRelationTable?.to)
    val defenceDamageRelationTable: List<TypeDamageValue> = formatAndSortDamageRelations(selectedRelationTable?.from)

    fun toggleType(type: PokemonType) : TypeChartState {
        val maxTypesSelectedAlready = selectedTypes.size == MAX_SELECTED_TYPES
        val typeWasSelectedPreviously = type in selectedTypes

        // if search query contains max selectable types, first delete first selected one
        val newSelectedTypes = when {
            maxTypesSelectedAlready && !typeWasSelectedPreviously ->
                selectedTypes - selectedTypes.first() + type
            typeWasSelectedPreviously ->
                selectedTypes - type
            else ->
                selectedTypes + type
        }

        return copy(selectedTypes = newSelectedTypes)
    }

    private fun formatAndSortDamageRelations(table: Map<PokemonType, Float>?): List<TypeDamageValue> {
        if (table == null) {
            return emptyList()
        }
        return table.map { (key, value) -> TypeDamageValue(key, value) }
            .sortedBy { -it.value }
    }

    companion object {

        private const val MAX_SELECTED_TYPES = 2
    }
}