package io.github.lexadiky.pdx.feature.generic.list.domain

import arrow.core.Either
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem

interface GenericListItemDataSource<T: GenericListItem> {

    suspend fun load(): Either<Error, List<T>>

    sealed interface Error {

        object Generic : Error
    }
}
