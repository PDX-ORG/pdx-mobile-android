plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.arc"
}

dependencies {
    api(libs.kotlin.coroutines)
    api(libs.arrow.core)

    implementation(libs.koin.compose)
    implementation(libs.android.compose.ui.core)
    implementation(libs.android.compose.viewmodel)
}