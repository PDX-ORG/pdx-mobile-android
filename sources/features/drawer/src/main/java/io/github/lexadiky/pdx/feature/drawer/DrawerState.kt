package io.github.lexadiky.pdx.feature.drawer

import io.github.lexadiky.pdx.feature.drawer.entity.DrawerItem

internal data class DrawerState(
    val items: List<DrawerItem> = emptyList()
)
