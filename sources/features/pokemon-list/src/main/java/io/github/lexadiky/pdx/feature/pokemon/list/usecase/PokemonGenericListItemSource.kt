package io.github.lexadiky.pdx.feature.pokemon.list.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListItemDataSource
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.feature.pokemonlist.R
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.format
import io.github.lexadiky.pdx.lib.resources.string.from

internal class PokemonGenericListItemSource(
    private val getPokemon: GetPokemonPreviewUseCase
) : GenericListItemDataSource<PokemonGenericListItem> {

    override suspend fun load(): Either<GenericListItemDataSource.Error, List<PokemonGenericListItem>> {
        return getPokemon()
            .map { pokemons -> pokemons.map { pokemon -> transformToGenericListItem(pokemon) } }
            .mapLeft { GenericListItemDataSource.Error.Generic }
    }

    private fun transformToGenericListItem(pokemon: PokemonPreview) =
        PokemonGenericListItem(
            id = pokemon.name,
            note = StringResource.from(R.string.pokemon_list_national_id_template)
                .format(pokemon.nationalDexNumber),
            title = StringResource.from(pokemon.localNames[PokemonLanguage.ENGLISH]!!),
            primaryImage = pokemon.normalSprite?.let { ImageResource.from(it) }
                ?: ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball),
            secondaryImage = pokemon.shinySprite?.let { ImageResource.from(it) }
                ?: ImageResource.from(io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball),
            tags = pokemon.types.map { type ->
                GenericListItem.Tag(type.toStringResource(), type.toColorResource())
            },
            textSearchIndex = makeTextSearchIndex(pokemon),
            types = pokemon.types
        )

    private fun makeTextSearchIndex(pokemon: PokemonPreview): String {
        return pokemon.localNames.values.joinToString()
    }
}
