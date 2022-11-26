package io.github.lexadiky.pdx.feature.settings.achievement

import io.github.lexadiky.pdx.lib.arc.di.module

val AchievementSettingsModule by module {
    viewModel { AchievementSettingsViewModel(inject()) }
}