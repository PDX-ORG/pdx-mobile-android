package io.github.lexadiky.pdx.domain.pokemon.util

import arrow.core.Either
import io.github.lexadiky.pdx.lib.blogger.BLogger
import io.github.lexadiky.pdx.lib.blogger.error
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class ResourcesLoader(private val json: Json) {

    inline fun <reified T> load(path: String): Either<Error, T> = Either.catch {
        val body = Thread.currentThread().contextClassLoader!!
            .getResourceAsStream(path)
            .bufferedReader()
            .readText()
        json.decodeFromString<T>(body)
    }.mapLeft { throwable ->
        BLogger.error("can't load resource body", throwable)
        Error
    }

    object Error
}