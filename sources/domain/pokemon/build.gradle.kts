plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "io.github.lexadiky.pdx.domain.pokemon"
    buildFeatures {
        compose = false
    }
}

dependencies {
    implementation(projects.libs.network)
    implementation(projects.libs.resources)
}