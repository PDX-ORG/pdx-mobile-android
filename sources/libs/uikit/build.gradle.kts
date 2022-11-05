plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

val compose_version = "1.3.0"

dependencies {
    api(libs.android.core)
    api(libs.android.lifecycle.runtime)
    api(libs.android.activity.compose)
    api(libs.android.compose.ui.core)
    api(libs.android.compose.ui.graphics)
    api(libs.android.compose.ui.preview)
    api(libs.android.compose.material3)
}