package io.github.lexadiky.pdx.feature.settings

import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsViewModel
import io.github.lexadiky.pdx.lib.arc.di.module

val SettingsPageModule by module {
    internal {
        viewModel { SettingsPageViewModel(inject(), inject()) }
        viewModel { AchievementSettingsViewModel(inject()) }
    }
}