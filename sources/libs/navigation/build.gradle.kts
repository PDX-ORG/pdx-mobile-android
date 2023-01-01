plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
    id("io.github.lexadiky.pdx.plugin.eve")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.navigation"
}

dependencies {
    api(libs.android.navigation.compose)
    implementation(projects.libs.arc)
    implementation(projects.libs.analytics)
    implementation(projects.libs.uikit)
}