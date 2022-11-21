package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di

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
    NavHost(navController = di.inject(), startDestination = START_DESTINATION) {
        buildFn()
    }
}
