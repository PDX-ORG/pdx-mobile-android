package io.github.lexadiky.pdx.feature.typechart.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelations
import kotlinx.coroutines.launch

internal class TypeChartViewModel(
    private val getPokemonDamageRelations: GetPokemonTypeDamageRelations
) : ViewModel() {

    var state by mutableStateOf(TypeChartState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                damageRelationsSubState = state.damageRelationsSubState.copy(
                    damageRelations = getPokemonDamageRelations().orNull().orEmpty() // TODO add error handling
                )
            )
        }
    }

    fun onTypeClicked(type: PokemonType) {
        state = state.toggleType(type)
    }
}