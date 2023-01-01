package io.github.lexadiky.pdx.feature.generic.list.entity

interface SearchQuery<T: GenericListItem> {

    fun apply(items: List<T>): List<T>
}