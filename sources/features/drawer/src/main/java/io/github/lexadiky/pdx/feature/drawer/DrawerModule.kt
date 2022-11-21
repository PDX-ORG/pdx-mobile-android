package io.github.lexadiky.pdx.feature.drawer

import io.github.lexadiky.pdx.lib.arc.di.module

internal val DrawerModule by module {
    viewModel { DrawerViewModel(inject()) }
}