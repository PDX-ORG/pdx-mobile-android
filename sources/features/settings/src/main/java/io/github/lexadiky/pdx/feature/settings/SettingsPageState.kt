package io.github.lexadiky.pdx.feature.settings

import io.github.lexadiky.pdx.ui.uikit.theme.custom.CustomTheme

internal data class SettingsPageState(
    val availableThemes: List<CustomTheme> = emptyList(),
    val currentTheme: CustomTheme? = null
)