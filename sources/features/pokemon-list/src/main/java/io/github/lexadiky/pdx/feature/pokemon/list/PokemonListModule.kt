package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.feature.generic.list.GenericListViewModel
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListItemDataSource
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListNavigator
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilterViewModel
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.PokemonGenericListItemSource
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.PokemonGenericListNavigator
import io.github.lexadiky.pdx.lib.arc.di.module

internal val PokemonListModule by module {
    import(PokemonDomainModule)
    internal {
        viewModel { PokemonFilterViewModel(inject()) }
        viewModel {
            GenericListViewModel<PokemonGenericListItem>(
                dataSource = inject(),
                navigator = inject()
            )
        }
        single<GenericListItemDataSource<PokemonGenericListItem>> { PokemonGenericListItemSource(inject()) }
        single<GenericListNavigator<PokemonGenericListItem>> { PokemonGenericListNavigator(inject()) }

    }
}