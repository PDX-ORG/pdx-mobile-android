package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.pdx.feature.pokemon.list.usecase.GetPokemonUseCase
import io.github.lexadiky.pdx.lib.arc.di.module

val PokemonListModule by module {
    viewModel { PokemonListViewModel(inject(), inject()) }
    single { GetPokemonUseCase(inject()) }
}