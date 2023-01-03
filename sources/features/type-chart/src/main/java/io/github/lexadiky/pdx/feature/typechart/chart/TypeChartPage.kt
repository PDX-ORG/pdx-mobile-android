@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.typechart.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.typechart.R
import io.github.lexadiky.pdx.feature.typechart.ui.EffectChart
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes

@Composable
internal fun TypeChartPage(viewModel: TypeChartViewModel = di.inject()) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TypeSelectionCard(viewModel.state, viewModel::onTypeClicked)

        EffectChart(
            title = stringResource(id = R.string.type_chart_section_attack_title),
            table = viewModel.state.attackDamageRelationTable,
            modifier = Modifier.padding(MaterialTheme.sizes.s2)
        )
        EffectChart(
            title = stringResource(id = R.string.type_chart_section_defence_title),
            table = viewModel.state.defenceDamageRelationTable,
            modifier = Modifier.padding(MaterialTheme.sizes.s2)
        )
    }
}

@Composable
private fun TypeSelectionCard(state: TypeChartState, onTypeClicked: (PokemonType) -> Unit) {
    Card(modifier = Modifier.padding(MaterialTheme.sizes.s2)) {
        Column(
            modifier = Modifier.padding(MaterialTheme.sizes.s2)
        ) {
            state.allTypes.chunked(3).forEach { typeChunk ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s1)
                ) {
                    typeChunk.forEach { type ->
                        FilterChip(
                            selected = type in state.selectedTypes,
                            onClick = { onTypeClicked(type) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = type.toColorResource().render(),
                                selectedLabelColor = MaterialTheme.colorScheme.onError
                            ),
                            label = { Text(text = type.toStringResource().render().value) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

