@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.typechart.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import io.github.lexadiky.pdx.feature.typechart.R
import io.github.lexadiky.pdx.feature.typechart.entity.TypeDamageValue
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.util.saturation
import io.github.lexadiky.pdx.ui.uikit.widget.PillChip
import io.github.lexadiky.pdx.ui.uikit.widget.PillChipDefaults

@Composable
internal fun TypeChartPage(viewModel: TypeChartViewModel = di.inject()) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        FlowRow(
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
            mainAxisSpacing = MaterialTheme.sizes.s1,
            modifier = Modifier.padding(MaterialTheme.sizes.s2)
        ) {
            viewModel.state.allTypes.forEach { type ->
                FilterChip(
                    selected = type in viewModel.state.selectedTypes,
                    onClick = { viewModel.onTypeClicked(type) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = type.toColorResource().render(),
                        selectedLabelColor = MaterialTheme.colorScheme.onError
                    ),
                    label = { Text(text = type.toStringResource().render().value) }
                )
            }
        }

        EffectChart(stringResource(id = R.string.type_chart_section_attack_title), viewModel.state.attackDamageRelationTable)
        EffectChart(stringResource(id = R.string.type_chart_section_defence_title), viewModel.state.defenceDamageRelationTable)
    }
}

@Composable
private fun EffectChart(title: String, table: List<TypeDamageValue>) {
    if (table.isNotEmpty()) {
        Column(modifier = Modifier.padding(MaterialTheme.sizes.s2)) {
            Text(text = title, style = MaterialTheme.typography.headlineMedium)
            FlowRow(
                mainAxisSpacing = MaterialTheme.sizes.s1,
                crossAxisSpacing = MaterialTheme.sizes.s1,
                modifier = Modifier.padding(vertical = MaterialTheme.sizes.s2)
            ) {

                table.forEach { relation ->
                    val labelColor = relation.type.toColorResource().render()
                    PillChip(
                        label = { Text(text = relation.type.toStringResource().render().value) },
                        labelColor = labelColor,
                        trail = { Text(text = stringResource(id = R.string.type_chart_modifier, relation.value)) },
                        trailColor = PillChipDefaults.trailColor(labelColor),
                        textColor = MaterialTheme.colorScheme.onError
                    )
                }
            }
        }
    }
}
