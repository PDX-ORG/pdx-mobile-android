package io.github.lexadiky.pdx.feature.pokemon.list.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.feature.pokemon.list.entity.domain.PokemonPreview
import io.github.lexadiky.pdx.lib.blogger.BLogger
import io.github.lexadiky.pdx.lib.blogger.error
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class GetPokemonUseCase(private val json: Json) {

    suspend operator fun invoke(): Either<Error, List<PokemonPreview>> = Either.catch {
        val pokemonPreviewBody = Thread.currentThread().contextClassLoader
            .getResourceAsStream("discovery/pokemon.json")
            .bufferedReader()
            .readText()

        json.decodeFromString<List<PokemonPreview>>(pokemonPreviewBody)
    }.mapLeft { throwable ->
        BLogger.error("can't load pokemon preview list", throwable)
        Error
    }

    object Error
}