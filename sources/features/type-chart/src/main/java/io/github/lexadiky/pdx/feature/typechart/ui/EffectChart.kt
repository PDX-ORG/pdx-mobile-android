package io.github.lexadiky.pdx.feature.typechart.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.flowlayout.FlowRow
import io.github.lexadiky.pdx.feature.typechart.R
import io.github.lexadiky.pdx.feature.typechart.entity.TypeDamageValue
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.widget.PillChip
import io.github.lexadiky.pdx.ui.uikit.widget.PillChipDefaults

@Composable
internal fun EffectChart(
    title: String,
    table: List<TypeDamageValue>,
    modifier: Modifier = Modifier
) {
    if (table.isNotEmpty()) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                FlowRow(
                    mainAxisSpacing = MaterialTheme.sizes.s1,
                    crossAxisSpacing = MaterialTheme.sizes.s1,
                    modifier = Modifier.padding(MaterialTheme.sizes.s2)
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
}