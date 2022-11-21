plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.resources"
    buildFeatures {
        compose = false
    }
}

dependencies {
    api(libs.android.compose.ui.core)
}