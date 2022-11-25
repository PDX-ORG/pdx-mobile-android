package io.github.lexadiky.pdx.ui.uikit

import io.github.lexadiky.pdx.lib.arc.di.module
import io.github.lexadiky.pdx.ui.uikit.util.ShakeDetector

val UikitModule by module {
    single { ShakeDetector(inject()) }
}