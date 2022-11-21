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
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.*
import kotlinx.coroutines.launch

internal class DrawerViewModel(
    private val navigator: Navigator
) : ViewModel() {

    var state by mutableStateOf(DrawerState())
        private set

    init {
        state = state.copy(
            items = listOf(
                DrawerItem.UserAccount,
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Build),
                    title = StringResource.from("Item"),
                    selected = false,
                    route = "pdx://index"
                ),
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Build),
                    title = StringResource.from("Item"),
                    selected = false,
                    route = "pdx://index"
                ),
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Build),
                    title = StringResource.from("Item"),
                    selected = false,
                    route = "pdx://index"
                ),
                DrawerItem.Divider,
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Notifications),
                    title = StringResource.from(R.string.drawer_item_news_title),
                    selected = false,
                    route = "pdx://news"
                ),
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Settings),
                    title = StringResource.from(R.string.drawer_item_settings_title),
                    selected = false,
                    route = "pdx://settings"
                ),
                DrawerItem.Divider,
                DrawerItem.Login
            )
        )
    }

    fun onItemClicked(drawerItem: DrawerItem) {
        if (drawerItem is DrawerItem.Navigation) {
            viewModelScope.launch {
                navigator.navigate(drawerItem.route)
            }
        }
    }
}