package io.github.lexadiky.pdx.ui.uikit.theme.custom

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import io.github.lexadiky.pdx.lib.blogger.BLogger
import io.github.lexadiky.pdx.lib.blogger.error
import io.github.lexadiky.pdx.lib.blogger.info
import io.github.lexadiky.pdx.lib.blogger.verbose
import kotlinx.coroutines.delay

class ThemeManager(
    private val context: Context,
    private val isDark: Boolean
) {
    private val sharedPrefs = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    private val factory: CustomThemeFactory = CustomThemeFactory(context)
    private val currentTheme: MutableState<CustomTheme> = mutableStateOf(factory.default(isDark))

    init {
        BLogger.tag("ThemeManager").verbose("created")
        val selectedThemeId = sharedPrefs.getString(SP_PREF_ID + isDark, null)
        if (selectedThemeId != null) {
            set(selectedThemeId)
        } else {
            BLogger.tag("ThemeManager").verbose("no theme set by user, using default")
        }
    }

    fun list(): List<CustomTheme> {
        return factory.create().filter { it.isDark == isDark }
    }

    fun set(id: String): Boolean {
        val theme = list().firstOrNull { it.id == id && it.isDark == isDark }
        if (theme == null) {
            BLogger.tag("ThemeManager").error("can't find theme: '$id' to set, doing nothing")
            return false
        }
        sharedPrefs.edit()
            .putString(SP_PREF_ID + isDark, id)
            .apply()
        currentTheme.value = theme
        BLogger.tag("ThemeManager").info("theme changed to: `$id`")
        return true
    }

    fun current(): CustomTheme {
        return currentTheme.value
    }

    companion object {

        private const val SP_NAME = "theme_manager"
        private const val SP_PREF_ID = "current_theme_"
    }
}