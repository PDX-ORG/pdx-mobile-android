package io.github.lexadiky.pdx.feature.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.feature.drawer.domain.DrawerItemSource
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.*
import io.github.lexadiky.pdx.lib.uikit.R.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class DrawerViewModel(
    private val navigator: Navigator,
    private val drawerItemSource: DrawerItemSource
) : ViewModel() {

    var state by mutableStateOf(DrawerState())
        private set

    init {
        viewModelScope.launch {
            drawerItemSource.get().collectLatest { newItems ->
                state = state.copy(
                    items = newItems
                )
            }
        }
    }

    fun onItemClicked(drawerItem: DrawerItem) {
        if (drawerItem is DrawerItem.Navigation) {
            viewModelScope.launch {
                navigator.navigate(drawerItem.route)
            }
        }
    }
}