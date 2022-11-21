package io.github.lexadiky.pdx.feature.settings

import io.github.lexadiky.pdx.lib.arc.di.module

val SettingsPageModule by module {
    viewModel { SettingsPageViewModel(inject(), inject()) }
}