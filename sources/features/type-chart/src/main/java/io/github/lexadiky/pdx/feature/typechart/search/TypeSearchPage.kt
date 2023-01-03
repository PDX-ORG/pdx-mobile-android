@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package io.github.lexadiky.pdx.feature.typechart.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.feature.typechart.R
import io.github.lexadiky.pdx.feature.typechart.ui.EffectChart
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.sizes
import io.github.lexadiky.pdx.ui.uikit.widget.LargeWikiPreview
import io.github.lexadiky.pdx.ui.uikit.widget.SearchField
import io.github.lexadiky.pdx.ui.uikit.widget.SmallWikiPreview
import io.github.lexadiky.pdx.ui.uikit.widget.TagItem

@Composable
internal fun TypeSearchPage(viewModel: TypeSearchViewModel = di.inject()) {
    val keyboardController = LocalSoftwareKeyboardController.current

    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    LazyColumn(
        contentPadding = PaddingValues(MaterialTheme.sizes.s2),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.sizes.s2)
    ) {
        item {
            SearchField(
                text = viewModel.state.searchQuery,
                onTextChanged = { viewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        if (viewModel.state.selectedPokemon == null) {
            items(viewModel.state.suggestedPokemon) { pokemon ->
                SmallWikiPreview(
                    title = pokemon.name.render().value,
                    preTitle = pokemon.nationalId.render().value,
                    icon = pokemon.image.render(
                        transformations = listOf(ImageTransformation.CROP_TRANSPARTENT)
                    ),
                    onClick = {
                        keyboardController?.hide()
                        viewModel.selectHint(pokemon)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        viewModel.state.selectedPokemon?.let { pokemon ->
            item {
                LargeWikiPreview(
                    title = pokemon.name.render().value,
                    preTitle = pokemon.nationalId.render().value,
                    image = pokemon.image.render(
                        transformations = listOf(ImageTransformation.CROP_TRANSPARTENT)
                    ),
                    tags = pokemon.types.map { type ->
                        TagItem(
                            title = type.toStringResource(),
                            color = type.toColorResource()
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            Spacer(modifier = Modifier.size(MaterialTheme.sizes.s2))
            EffectChart(stringResource(id = R.string.type_chart_section_attack_title), viewModel.state.attackDamageRelationTable)
            Spacer(modifier = Modifier.size(MaterialTheme.sizes.s4))
            EffectChart(stringResource(id = R.string.type_chart_section_defence_title), viewModel.state.defenceDamageRelationTable)
        }
    }
}
