package io.github.lexadiky.pdx.feature.typechart.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

class PokemonTypeSearchItem(
    val name: StringResource,
    val image: ImageResource,
    val searchQueryIndex: String,
    val nationalId: StringResource,
    val types: List<PokemonType>
)