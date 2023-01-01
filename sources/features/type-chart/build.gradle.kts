plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.feature.typechart"
}

dependencies {
    implementation(projects.features.genericList)
    implementation(projects.domain.pokemon)

    implementation(projects.libs.arc)
    implementation(projects.libs.uikit)
    implementation(projects.libs.errorHandler)
    implementation(projects.libs.navigation)
    implementation(projects.libs.blogger)

    implementation(projects.domain.pokemon)
}