package io.github.lexadiky.pdx.feature.typechart.search

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.feature.typechart.entity.DamageRelationsSubState
import io.github.lexadiky.pdx.feature.typechart.entity.PokemonTypeSearchItem
import io.github.lexadiky.pdx.feature.typechart.entity.TypeDamageValue
import io.github.lexadiky.pdx.lib.errorhandler.UIError

data class TypeSearchState(
    val allPokemon: List<PokemonTypeSearchItem> = emptyList(),
    val searchQuery: String = "",
    val selectedPokemon: PokemonTypeSearchItem? = null,
    val suggestedPokemon: List<PokemonTypeSearchItem> = emptyList(),
    val error: UIError? = null,
    val damageRelationsSubState: DamageRelationsSubState = DamageRelationsSubState()
) {

    val selectedTypes get() = damageRelationsSubState.selectedTypes
    val attackDamageRelationTable: List<TypeDamageValue> get() = damageRelationsSubState.attackDamageRelationTable
    val defenceDamageRelationTable: List<TypeDamageValue> get() = damageRelationsSubState.defenceDamageRelationTable

    fun setSelectedPokemon(selectedPokemon: PokemonTypeSearchItem?) : TypeSearchState {
        return copy(
            selectedPokemon = selectedPokemon,
            damageRelationsSubState = damageRelationsSubState.copy(
                selectedTypes = selectedPokemon?.types.orEmpty()
            )
        )
    }
}