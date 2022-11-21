package io.github.lexadiky.pdx.feature.drawer.entity

import io.github.lexadiky.pdx.lib.navigation.NavigationRoute
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

internal sealed interface DrawerItem {

    data class Navigation(
        val icon: ImageResource,
        val title: StringResource,
        val selected: Boolean,
        val route: NavigationRoute
    ) : DrawerItem

    object Divider : DrawerItem
    object UserAccount : DrawerItem
    object Login : DrawerItem
}