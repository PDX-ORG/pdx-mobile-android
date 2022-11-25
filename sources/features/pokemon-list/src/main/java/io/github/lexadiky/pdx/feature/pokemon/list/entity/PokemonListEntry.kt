package io.github.lexadiky.pdx.feature.pokemon.list.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

internal data class PokemonListEntry(
    val id: String,
    val nationalId: StringResource,
    val name: StringResource,
    val primaryColor: ColorResource,
    val secondaryColor: ColorResource,
    val image: ImageResource,
    val alternativeImage: ImageResource,
    val types: List<PokemonType>
)