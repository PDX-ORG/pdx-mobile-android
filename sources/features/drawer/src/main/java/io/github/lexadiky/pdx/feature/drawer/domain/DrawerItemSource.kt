package io.github.lexadiky.pdx.feature.drawer.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem
import io.github.lexadiky.pdx.lib.ifEnabled
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.lib.uikit.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class DrawerItemSource(private val navigator: Navigator) {

    suspend fun get(): Flow<List<DrawerItem>> {
        return flowOf(
            listOfNotNull(
                DrawerItem.UserAccount,
                DrawerItem.Navigation(
                    icon = ImageResource.from(R.drawable.uikit_ic_pokeball),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_pokemon_title),
                    selected = false,
                    route = "pdx://pokemon"
                ),
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Star),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_type_chart_title),
                    selected = false,
                    route = "pdx://type"
                ),
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Build),
                    title = StringResource.from("Item"),
                    selected = false,
                    route = "pdx://index"
                ),
                DrawerItem.Divider,
                newsItem(),
                DrawerItem.Navigation(
                    icon = ImageResource.from(Icons.Default.Settings),
                    title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_settings_title),
                    selected = false,
                    route = "pdx://settings"
                ),
                DrawerItem.Divider,
                DrawerItem.Login
            )
        )
    }

    private suspend fun newsItem(): DrawerItem? {
        return if (navigator.hasRoute("pdx://news")) {
            DrawerItem.Navigation(
                icon = ImageResource.from(Icons.Default.Notifications),
                title = StringResource.from(io.github.lexadiky.pdx.feature.drawer.R.string.drawer_item_news_title),
                selected = false,
                route = "pdx://news"
            )
        } else {
            null
        }
    }
}