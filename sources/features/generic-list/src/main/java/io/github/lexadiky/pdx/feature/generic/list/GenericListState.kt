package io.github.lexadiky.pdx.feature.generic.list

import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery
import io.github.lexadiky.pdx.lib.errorhandler.UIError

data class GenericListState<T: GenericListItem>(
    val items: List<T> = emptyList(),
    val query: SearchQuery<T>? = null,
    val searchActivated: Boolean = false,
    val searchAvailable: Boolean = false,
    val useAlternativeImages: Boolean = false,
    val uiError: UIError? = null
) {

    val visibleItems: List<T> = query?.apply(items) ?: items
}
