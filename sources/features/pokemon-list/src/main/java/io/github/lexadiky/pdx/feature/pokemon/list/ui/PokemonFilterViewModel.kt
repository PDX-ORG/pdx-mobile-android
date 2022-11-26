package io.github.lexadiky.pdx.feature.pokemon.list.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.list.entity.SearchQuery

internal class PokemonFilterViewModel(private val queryChangedCallback: (SearchQuery) -> Unit) :
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
        if (state.query.selectedTypes.size == MAX_TYPES) {
            state = state.copy(
                query = state.query.copy(
                    selectedTypes = state.query.selectedTypes - state.query.selectedTypes.first()
                )
            )
        }
        state = if (type in state.query.selectedTypes) {
            state.copy(
                query = state.query.copy(
                    selectedTypes = state.query.selectedTypes - type
                )
            )
        } else {
            state.copy(
                query = state.query.copy(
                    selectedTypes = state.query.selectedTypes + type
                )
            )
        }
        queryChangedCallback(state.query)
    }

    companion object {

        private const val MAX_TYPES = 2
    }
}