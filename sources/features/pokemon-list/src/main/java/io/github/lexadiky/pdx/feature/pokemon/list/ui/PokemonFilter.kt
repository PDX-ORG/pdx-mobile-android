@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.pokemon.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.feature.pokemonlist.R
import io.github.lexadiky.pdx.lib.arc.di.di


@Composable
internal fun PokemonFilter(viewModel: PokemonFilterViewModel = di.inject()) {
    Column {
        SearchQuery(viewModel.state.query.text) {
            viewModel.updateTextQuery(it)
        }
        Row {
            if (viewModel.state.showTypeFilterDialog) {
                TypeFilterDialog(
                    onClear = { viewModel.hideTypeFilterDialog(clearTypeFilters = true) },
                    onOk = { viewModel.hideTypeFilterDialog(clearTypeFilters = false) },
                    onItemClicked = { viewModel.onTypeSelected(it) },
                    selected = viewModel.state.query.selectedTypes
                )
            }
            FilterChip(
                selected = false,
                onClick = { viewModel.showTypeFilterDialog() },
                label = { Text(text = stringResource(id = R.string.pokemon_list_query_type_button)) }
            )
        }
    }
}

@Composable
private fun SearchQuery(text: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        placeholder = { Text(stringResource(id = R.string.pokemon_list_query_placeholder)) },
        label = { Text(stringResource(id = R.string.pokemon_list_query_label)) },
        trailingIcon = { Icon(Icons.Default.Search, null) },
        onValueChange = onTextChanged,
        modifier = Modifier.fillMaxWidth()
    )
}