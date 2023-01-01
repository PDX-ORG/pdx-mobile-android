package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.util.ResourcesLoader

class GetPokemonTypeDamageRelations internal constructor(private val loader: ResourcesLoader) {

    suspend operator fun invoke(): Either<Error, Map<PokemonType, PokemonTypeDamageRelation>> =
        loader.load<Map<PokemonType, PokemonTypeDamageRelation>>("type_chart.json")
            .mapLeft { Error }

    object Error
}