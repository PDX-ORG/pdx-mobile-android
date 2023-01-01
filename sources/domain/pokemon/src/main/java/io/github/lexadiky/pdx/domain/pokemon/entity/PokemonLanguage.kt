package io.github.lexadiky.pdx.domain.pokemon.entity

import kotlinx.serialization.SerialName

enum class PokemonLanguage {
    @SerialName("roomaji")
    JA_ROOMAJI,
    @SerialName("en")
    ENGLISH,
    @SerialName("ja")
    JAPANESE
}