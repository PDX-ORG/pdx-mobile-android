package io.github.lexadiky.pdx.lib

import io.github.lexadiky.pdx.lib.arc.di.module

val FeatureToggleModule by module {
    single { FeatureToggleManager() }
}