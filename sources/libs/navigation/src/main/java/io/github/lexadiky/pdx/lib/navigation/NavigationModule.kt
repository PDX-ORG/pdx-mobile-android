package io.github.lexadiky.pdx.lib.navigation

import androidx.navigation.NavHostController
import io.github.lexadiky.pdx.lib.arc.di.module

internal fun NavigationModule(controller: NavHostController) = module {
    single { controller }
    single { Navigator(inject()) }
}.value
