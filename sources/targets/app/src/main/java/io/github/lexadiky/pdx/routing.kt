package io.github.lexadiky.pdx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.lexadiky.pdx.feature.settings.achievement.AchievementSettingsPage
import io.github.lexadiky.pdx.feature.news.NewsFeatureToggle
import io.github.lexadiky.pdx.feature.news.NewsFeedPage
import io.github.lexadiky.pdx.feature.pokemon.details.PokemonDetailsPage
import io.github.lexadiky.pdx.feature.pokemon.list.PokemonListPage
import io.github.lexadiky.pdx.feature.settings.SettingsPage
import io.github.lexadiky.pdx.feature.typechart.TypePage
import io.github.lexadiky.pdx.lib.FeatureToggleManager
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.ifEnabled

@Composable
fun routing(): NavGraphBuilder.() -> Unit {
    val toggleManager = di.inject<FeatureToggleManager>()

    return remember(toggleManager) {
        {
            composable("pdx://settings") {
                SettingsPage()
            }
            composable("pdx://settings/achievements") {
                AchievementSettingsPage()
            }
            toggleManager.ifEnabled(NewsFeatureToggle) {
                composable("pdx://news") {
                    NewsFeedPage()
                }
            }
            composable("pdx://pokemon") {
                PokemonListPage()
            }
            composable("pdx://pokemon/{id}") { entry ->
                PokemonDetailsPage(
                    pokemonId = entry.arguments!!.getString("id")!!
                )
            }
            composable("pdx://type") {
                TypePage()
            }
        }
    }
}
