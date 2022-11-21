package io.github.lexadiky.pdx.feature.toolbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.launch

internal class ToolbarViewModel(
    private val connector: ToolbarConnector
) : ViewModel() {

    var state: ToolbarState by mutableStateOf(ToolbarState())
        private set

    suspend fun openDrawer() {
        connector.onDrawerToggled()
    }

    sealed interface Event {

        object OpenDrawer : Event
    }
}

