package io.github.lexadiky.pdx.feature.settings.achievement

import io.github.lexadiky.pdx.domain.achievement.library.Achievement

data class AchievementSettingsPageState(val availableAchievements: List<Achievement> = emptyList())