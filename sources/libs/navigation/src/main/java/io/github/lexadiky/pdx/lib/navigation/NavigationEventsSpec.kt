package io.github.lexadiky.pdx.lib.navigation

import io.github.lexadiky.pdx.lib.analytics.AnalyticsManager

internal class NavigationEventsSpec(private val analyticsManager: AnalyticsManager) {

    fun navigate(route: String) {
        analyticsManager.log(
            event = "dev_navigation_navigate",
            parameters = mapOf("route" to route)
        )
    }
}