package io.github.lexadiky.pdx.feature.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.lib.navigation.Navigator
import io.github.lexadiky.pdx.ui.uikit.theme.custom.CustomTheme
import io.github.lexadiky.pdx.ui.uikit.theme.custom.ThemeManager
import kotlinx.coroutines.launch

internal class SettingsPageViewModel(
    private val themeManager: ThemeManager,
    private val navigator: Navigator
) : ViewModel() {

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

    fun openGithub() {
        viewModelScope.launch {
            navigator.navigate("https://github.com/PDX-ORG/pdx-mobile-android")
        }
    }

    fun openAchievements() {
        viewModelScope.launch {
            navigator.navigate("pdx://settings/achievements")
        }
    }
}