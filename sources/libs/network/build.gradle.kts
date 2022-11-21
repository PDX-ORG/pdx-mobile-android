plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.network"
    buildFeatures {
        compose = false
    }
}

dependencies {
    api(projects.libs.arc)
    api(libs.kotlin.serialization.json)
    api(libs.ktor.core)
    api(libs.ktor.okhttp)
    api(libs.ktor.json)
    api(libs.ktor.contentNegotiation)
}