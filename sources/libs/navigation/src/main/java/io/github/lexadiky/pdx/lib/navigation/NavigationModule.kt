package io.github.lexadiky.pdx.lib.navigation

import androidx.navigation.NavHostController
import io.github.lexadiky.pdx.lib.arc.di.eagerModule
import io.github.lexadiky.pdx.lib.arc.di.module

internal fun NavigationModule(controller: NavHostController) = eagerModule {
    single { controller }
    single { Navigator(inject(), inject(), inject()) }
    single { NavigationEventsSpec(inject()) }
}
