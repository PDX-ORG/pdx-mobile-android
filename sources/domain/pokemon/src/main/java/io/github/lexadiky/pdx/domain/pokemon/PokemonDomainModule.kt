package io.github.lexadiky.pdx.domain.pokemon

import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelations
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.util.ResourcesLoader
import io.github.lexadiky.pdx.lib.arc.di.module

val PokemonDomainModule by module {
    single { GetPokemonPreviewUseCase(inject()) }
    single { GetPokemonTypeDamageRelations(inject()) }

    internal {
        single { ResourcesLoader(inject()) }
    }
}
