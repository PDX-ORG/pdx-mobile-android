package io.github.lexadiky.pdx.ui.uikit.theme.custom

import android.content.Context
import android.os.Build
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.material.color.utilities.Scheme
import io.github.lexadiky.pdx.ui.uikit.util.toColorScheme

internal class CustomThemeFactory(private val context: Context) {

    private val defaultThems = createDefaultThemes()

    fun create(): List<CustomTheme> {
        return defaultThems + createPokemonTypeThemes()
    }

    fun default(isDark: Boolean): CustomTheme {
        return defaultThems.first { it.isDark == isDark }
    }

    private fun createPokemonTypeThemes(): List<CustomTheme> {
        val typeColors = listOf(
            "type-normal" to Color(0xFFA8A77A),
            "type-fire" to Color(0xFFEE8130),
            "type-water" to Color(0xFF6390F0),
            "type-electric" to Color(0xFFF7D02C),
            "type-grass" to Color(0xFF7AC74C),
            "type-ice" to Color(0xFF96D9D6),
            "type-fighting" to Color(0xFFC22E28),
            "type-poison" to Color(0xFFA33EA1),
            "type-ground" to Color(0xFFE2BF65),
            "type-flying" to Color(0xFFA98FF3),
            "type-psychic" to Color(0xFFF95587),
            "type-bug" to Color(0xFFA6B91A),
            "type-rock" to Color(0xFFB6A136),
            "type-ghost" to Color(0xFF735797),
            "type-dragon" to Color(0xFF6F35FC),
            "type-dark" to Color(0xFF705746),
            "type-steel" to Color(0xFFB7B7CE),
            "type-fairy" to Color(0xFFD685AD)
        )

        return typeColors.map { (id, brand) ->
            CustomTheme(
                id = id,
                isDark = false,
                colorScheme = Scheme.light(brand.toArgb()).toColorScheme()
            )
        } + typeColors.map { (id, brand) ->
            CustomTheme(
                id = id,
                isDark = true,
                colorScheme = Scheme.dark(brand.toArgb()).toColorScheme()
            )
        }
    }

    private fun createDefaultThemes(): List<CustomTheme> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val dynamicDark = CustomTheme(
                id = "dynamic-dark",
                isDark = true,
                colorScheme = dynamicDarkColorScheme(context)
            )
            val dynamicLight = CustomTheme(
                id = "dynamic-light",
                isDark = false,
                colorScheme = dynamicLightColorScheme(context)
            )

            return listOf(dynamicLight, dynamicDark)
        } else {
            val dynamicDark = CustomTheme(
                id = "default-dark",
                isDark = true,
                colorScheme = darkColorScheme()
            )
            val dynamicLight = CustomTheme(
                id = "default-light",
                isDark = false,
                colorScheme = lightColorScheme()
            )

            return listOf(dynamicLight, dynamicDark)
        }
    }
}