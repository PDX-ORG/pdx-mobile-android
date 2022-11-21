package io.github.lexadiky.pdx.ui.uikit.theme.custom

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.lexadiky.pdx.lib.arc.di.DIModule
import io.github.lexadiky.pdx.lib.arc.di.module

@Composable
internal fun ThemeModule(context: Context, isDark: Boolean): Lazy<DIModule> = remember(context, isDark) {
    module {
        single { ThemeManager(inject(), isDark) }
    }
}
