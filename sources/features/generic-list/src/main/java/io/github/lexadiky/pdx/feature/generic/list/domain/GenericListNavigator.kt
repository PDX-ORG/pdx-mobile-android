package io.github.lexadiky.pdx.feature.generic.list.domain

import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem

interface GenericListNavigator<T: GenericListItem> {

    suspend fun navigateToDetails(item: GenericListItem)
}