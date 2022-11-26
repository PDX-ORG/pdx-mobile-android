@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.settings.achievement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.feature.settings.R
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.arc.di.module
import io.github.lexadiky.pdx.ui.uikit.resources.render

@Composable
fun AchievementSettingsPage() {
    DIFeature(AchievementSettingsModule) {
        AchievementSettingsPageImpl()
    }
}

@Composable
private fun AchievementSettingsPageImpl(viewModel: AchievementSettingsViewModel = di.inject()) {
    LazyColumn {
        items(viewModel.state.availableAchievements) { achievement ->
            ListItem(
                headlineText = { Text(achievement.name.render().value) },
                supportingText = { Text(achievement.description.render().value) },
                trailingContent = {
                    Icon(
                        painter = achievement.icon?.render()
                            ?: painterResource(id = io.github.lexadiky.pdx.lib.uikit.R.drawable.uikit_ic_pokeball),
                        contentDescription = null)
                }
            )
        }
        item { Divider() }
        item {
            ListItem(
                headlineText = { Text(stringResource(id = R.string.settings_achievements_option_forget_title)) },
                supportingText = { Text(stringResource(id = R.string.settings_achievements_option_forget_subtitle)) },
                trailingContent = { Icon(Icons.Default.Delete, null) },
                modifier = Modifier.clickable { viewModel.dropAllAchievementData() }
            )
        }
    }
}