package io.github.lexadiky.pdx.feature.typechart.chart

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelationTable
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.util.times
import io.github.lexadiky.pdx.feature.typechart.entity.DamageRelationsSubState
import io.github.lexadiky.pdx.feature.typechart.entity.TypeDamageValue
import java.util.Comparator

data class TypeChartState(
    val damageRelationsSubState: DamageRelationsSubState = DamageRelationsSubState(),
) {

    val selectedTypes get() = damageRelationsSubState.selectedTypes
    val attackDamageRelationTable: List<TypeDamageValue> get() = damageRelationsSubState.attackDamageRelationTable
    val defenceDamageRelationTable: List<TypeDamageValue> get() = damageRelationsSubState.defenceDamageRelationTable

    val allTypes: List<PokemonType> = PokemonType.values().toList()

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

        return copy(
            damageRelationsSubState = damageRelationsSubState.copy(
                selectedTypes = newSelectedTypes
            )
        )
    }

    companion object {

        private const val MAX_SELECTED_TYPES = 2
    }
}