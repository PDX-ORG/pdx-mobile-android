package io.github.lexadiky.pdx.feature.news.feed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.feature.news.domain.GetNewsFeedUseCase
import io.github.lexadiky.pdx.feature.news.entity.NewsFeedItem
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.lib.navigation.util.navigate
import kotlinx.coroutines.launch

internal class NewsFeedViewModel(
    private val getNewsFeedUseCase: GetNewsFeedUseCase,
    private val navigator: Navigator
) : ViewModel() {

    var state by mutableStateOf(NewsFeedState())
        private set

    init {
        viewModelScope.launch {
            when (val eitherFeed = getNewsFeedUseCase()) {
                is Either.Left -> state = state.copy(error = eitherFeed.value)
                is Either.Right -> state = state.copy(items = eitherFeed.value)
            }
        }
    }

    fun dismissError() {
        state = state.copy(error = null)
    }

    fun openNewsItem(item: NewsFeedItem) {
        viewModelScope.launch {
            navigator.navigate(item.uri)
        }
    }

    fun openNewsItemAuthor(item: NewsFeedItem) {
        viewModelScope.launch {
            navigator.navigate(item.authorUri)
        }
    }
}