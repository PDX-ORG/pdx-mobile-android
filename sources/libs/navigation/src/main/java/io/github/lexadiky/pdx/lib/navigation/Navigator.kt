package io.github.lexadiky.pdx.lib.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavHostController


class Navigator internal constructor(
    private val context: Context,
    private val controller: NavHostController,
    private val navigationEventsSpec: NavigationEventsSpec
) {

    suspend fun navigate(route: String) {
        navigationEventsSpec.navigate(route)
        if (route.startsWith(LINK_PREFIX_HTTPS) || route.startsWith(LINK_PREFIX_HTTP)) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(route)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(browserIntent)
        } else {
            controller.navigate(route)
        }
    }

    companion object {

        private const val LINK_PREFIX_HTTPS = "https://"
        private const val LINK_PREFIX_HTTP = "http://"
    }
}