package io.github.lexadiky.pdx.lib.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import io.github.lexadiky.pdx.lib.arc.di.eagerModule
import io.github.lexadiky.pdx.lib.arc.di.module
import io.github.lexadiky.pdx.generated.analytics.NavigationEventsSpec

internal fun NavigationModule(controller: NavHostController, navGraph: NavGraph) = eagerModule {
    single { controller }
    single { navGraph }
    single { Navigator(inject(), inject(), inject(), inject()) }

    internal {
        single { NavigationEventsSpec(inject()) }
    }
}
