package io.github.lexadiky.pdx.feature.drawer

import io.github.lexadiky.pdx.feature.drawer.domain.DrawerItemSource
import io.github.lexadiky.pdx.lib.arc.di.module

internal val DrawerModule by module {
    internal {
        single { DrawerItemSource(inject()) }
        viewModel { DrawerViewModel(inject(), inject()) }
    }
}