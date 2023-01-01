package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navDeepLink
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.navigation.util.registerPage404

private const val START_DESTINATION = "pdx://type"

object NavigationFeatureContext

@Composable
fun NavigationFeature(routing: NavGraphBuilder.() -> Unit, content: @Composable NavigationFeatureContext.() -> Unit) {
    val controller = rememberNavController()
    val navGraph = remember(controller, routing) {
        controller.setViewModelStore(ViewModelStore())
        val navGraph = controller.createGraph(START_DESTINATION, null, routing)
        navGraph
    }

    DIFeature(NavigationModule(controller, navGraph)) {
        NavigationFeatureContext.content()
    }
}

@Composable
fun NavigationFeatureContext.NavigationHost() {
    val navController = di.inject<NavHostController>()
    val graph = di.inject<NavGraph>()
    NavHost(navController, graph)
}
