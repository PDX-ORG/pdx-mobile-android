package io.github.lexadiky.pdx.feature.pokemon.list.entity.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class PokemonPreview(
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
    val types: List<String>
)