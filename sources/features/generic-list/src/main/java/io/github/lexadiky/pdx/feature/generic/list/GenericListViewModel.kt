package io.github.lexadiky.pdx.feature.generic.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import arrow.core.Either
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListItemDataSource
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListNavigator
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import kotlinx.coroutines.launch

class GenericListViewModel<T : GenericListItem>(
    private val dataSource: GenericListItemDataSource<T>,
    private val navigator: GenericListNavigator<T>
) : ViewModel() {

    var state by mutableStateOf(GenericListState<T>())
        private set

    init {
        loadFreshData()
    }

    fun toggleSearchMode() {
        state = state.copy(
            searchActivated = !state.searchActivated,
            query = null
        )
    }

    fun updateQuery(query: SearchQuery<T>) {
        state = state.copy(query = query)
    }

    fun openDetails(item: T) = viewModelScope.launch {
        navigator.navigateToDetails(item)
    }

    fun setSearchAvailable(isAvailable: Boolean) {
        state = state.copy(searchAvailable = isAvailable)
    }

    fun hideError() {
        state = state.copy(uiError = null)
    }

    private fun loadFreshData() = viewModelScope.launch {
        state = when (val data = dataSource.load()) {
            is Either.Left -> state.copy(uiError = UIError.default())
            is Either.Right -> state.copy(items = data.value)
        }
    }
}