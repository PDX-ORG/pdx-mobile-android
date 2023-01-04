package io.github.lexadiky.pdx.domain.pokemon.util

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelationTable
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType

operator fun PokemonTypeDamageRelation.plus(other: PokemonTypeDamageRelation): PokemonTypeDamageRelationTable {
    return PokemonTypeDamageRelationTable(
        from = this.asTable().from * other.asTable().from,
        to = this.asTable().to * other.asTable().to
    )
}

operator fun PokemonTypeDamageRelationTable.times(other: PokemonTypeDamageRelationTable): PokemonTypeDamageRelationTable {
    return PokemonTypeDamageRelationTable(
        from = this.from * other.from,
        to = this.to * other.to
    )
}

operator fun Map<PokemonType, Float>.times(other: Map<PokemonType, Float>): Map<PokemonType, Float> {
    return PokemonType.values().associateWith { type ->
        this.getOrDefault(type, 1f) * other.getOrDefault(type, 1f)
    }.filterValues { it != 1f }
}
