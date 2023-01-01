package io.github.lexadiky.pdx.domain.pokemon.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PokemonPreview(
    @SerialName("name")
    val name: String,
    @SerialName("national_dex_number")
    val nationalDexNumber: Int,
    @SerialName("local_names")
    val localNames: Map<PokemonLanguage, String>,
    @SerialName("normal_spite")
    val normalSprite: String?,
    @SerialName("shiny_sprite")
    val shinySprite: String?,
    @SerialName("types")
    val types: List<PokemonType>
)