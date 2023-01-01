package io.github.lexadiky.pdx.feature.typechart.entity

import io.github.lexadiky.pdx.feature.typechart.R
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

internal enum class TypePageTabs(
    val title: StringResource
) {

    Chart(
        title = StringResource.from(R.string.type_tab_chart_title)
    ),

    Search(
        title = StringResource.from(R.string.type_tab_search_title)
    )
}