package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilterViewModel
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.GetPokemonUseCase
import io.github.lexadiky.pdx.lib.arc.di.module

internal val PokemonListModule by module {
    viewModel { PokemonListViewModel(inject(), inject(), inject(), inject()) }
    viewModel { PokemonFilterViewModel(inject()) }
    single { GetPokemonUseCase(inject()) }
}