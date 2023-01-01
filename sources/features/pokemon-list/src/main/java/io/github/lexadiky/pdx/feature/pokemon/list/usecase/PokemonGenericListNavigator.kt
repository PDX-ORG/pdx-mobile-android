package io.github.lexadiky.pdx.feature.pokemon.list.usecase

import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListNavigator
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.lib.navigation.Navigator

class PokemonGenericListNavigator(
    private val navigator: Navigator
) : GenericListNavigator<PokemonGenericListItem> {

    override suspend fun navigateToDetails(item: GenericListItem) {
        navigator.navigate("pdx://pokemon/${item.id}")
    }
}