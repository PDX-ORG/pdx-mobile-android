package io.github.lexadiky.pdx.lib.navigation

import androidx.navigation.NavHostController

class Navigator(private val controller: NavHostController) {

    suspend fun navigate(route: String) {
        controller.navigate(route)
    }
}