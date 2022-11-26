package io.github.lexadiky.pdx.feature.settings.achievement

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lexadiky.pdx.domain.achievement.AchievementManager
import kotlinx.coroutines.launch

class AchievementSettingsViewModel(private val achievementManager: AchievementManager) : ViewModel() {

    var state: AchievementSettingsPageState by mutableStateOf(AchievementSettingsPageState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                availableAchievements = achievementManager.listGivenAchievements()
            )
        }
    }

    fun dropAllAchievementData() {
        achievementManager.drop()
        viewModelScope.launch {
            state = state.copy(
                availableAchievements = achievementManager.listGivenAchievements()
            )
        }
    }
}