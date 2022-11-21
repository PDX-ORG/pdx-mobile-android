package io.github.lexadiky.pdx.feature.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.lexadiky.pdx.ui.uikit.theme.custom.CustomTheme
import io.github.lexadiky.pdx.ui.uikit.theme.custom.ThemeManager

internal class SettingsPageViewModel(private val themeManager: ThemeManager) : ViewModel() {

    var state by mutableStateOf(SettingsPageState())
        private set

    init {
        state = state.copy(
            availableThemes = themeManager.list(),
            currentTheme = themeManager.current()
        )
    }

    fun onThemeSelected(theme: CustomTheme) {
        themeManager.set(theme.id)
        state = state.copy(
            currentTheme = themeManager.current()
        )
    }
}