plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.drawer"
}

dependencies {
    implementation(projects.libs.arc)
    implementation(projects.libs.uikit)
    implementation(projects.libs.navigation)
    implementation(projects.libs.featureToggle)
}