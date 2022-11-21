package io.github.lexadiky.pdx.feature.news.feed

import io.github.lexadiky.pdx.feature.news.entity.NewsFeedItem
import io.github.lexadiky.pdx.lib.errorhandler.UIError

internal data class NewsFeedState(
    val items: List<NewsFeedItem> = emptyList(),
    val error: UIError? = null
)
