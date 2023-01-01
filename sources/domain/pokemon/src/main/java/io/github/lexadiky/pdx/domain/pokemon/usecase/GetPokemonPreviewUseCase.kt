package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.util.ResourcesLoader

class GetPokemonPreviewUseCase internal constructor(private val loader: ResourcesLoader) {

    suspend operator fun invoke(): Either<Error, List<PokemonPreview>> =
        loader.load<List<PokemonPreview>>("discovery/pokemon.json")
            .mapLeft { Error }

    object Error
}