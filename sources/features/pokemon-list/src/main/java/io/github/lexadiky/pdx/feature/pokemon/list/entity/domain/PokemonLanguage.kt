package io.github.lexadiky.pdx.feature.pokemon.list.entity.domain

import kotlinx.serialization.SerialName

internal enum class PokemonLanguage {
    @SerialName("roomaji")
    JA_ROOMAJI,
    @SerialName("en")
    ENGLISH,
    @SerialName("ja")
    JAPANESE
}