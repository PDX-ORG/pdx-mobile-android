package io.github.lexadiky.pdx.feature.pokemon.list.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonSearchQuery

internal class PokemonFilterViewModel(private val queryChangedCallback: (PokemonSearchQuery) -> Unit) :
    ViewModel() {

    var state: PokemonFilterState by mutableStateOf(PokemonFilterState())
        private set

    fun showTypeFilterDialog() {
        state = state.copy(showTypeFilterDialog = true)
    }

    fun hideTypeFilterDialog(clearTypeFilters: Boolean) {
        state = state.copy(
            showTypeFilterDialog = false,
            query = state.query.copy(
                selectedTypes = if (clearTypeFilters) emptyList() else state.query.selectedTypes
            )
        )
        queryChangedCallback(state.query)
    }

    fun updateTextQuery(text: String) {
        state = state.copy(query = state.query.copy(text = text))
        queryChangedCallback(state.query)
    }

    fun onTypeSelected(type: PokemonType) {
        val maxTypesSelectedAlready = state.query.selectedTypes.size == MAX_TYPES
        val typeWasSelectedPreviously = type in state.query.selectedTypes

        // if search query contains max selectable types, first delete first selected one
        val newQuery = when {
            maxTypesSelectedAlready && !typeWasSelectedPreviously ->
                state.query.copy(
                    selectedTypes = state.query.selectedTypes - state.query.selectedTypes.first() + type
                )
            typeWasSelectedPreviously ->
                state.query.copy(
                    selectedTypes = state.query.selectedTypes - type
                )
            else ->
                state.query.copy(
                    selectedTypes = state.query.selectedTypes + type
                )
        }

        state = state.copy(query = newQuery)
        queryChangedCallback(state.query)
    }

    companion object {

        private const val MAX_TYPES = 2
    }
}