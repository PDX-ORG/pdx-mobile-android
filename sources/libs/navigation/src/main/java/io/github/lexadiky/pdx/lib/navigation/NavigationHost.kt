package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.navigation.util.registerPage404

private const val START_DESTINATION = "pdx://index"

object NavigationFeatureContext

@Composable
fun NavigationFeature(content: @Composable NavigationFeatureContext.() -> Unit) {
    val controller = rememberNavController()
    DIFeature(NavigationModule(controller)) {
        NavigationFeatureContext.content()
    }
}

@Composable
fun NavigationFeatureContext.NavigationHost(buildFn: NavGraphBuilder.() -> Unit) {
    val navController = di.inject<NavHostController>()
    NavHost(navController = navController, startDestination = START_DESTINATION) {
        buildFn()
        registerPage404()
    }
}
