plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.lexadiky.pdx.domain.achievement"
    buildFeatures {
        compose = false
    }
}

dependencies {
    implementation(projects.libs.arc)
    implementation(projects.libs.resources)
    implementation(projects.libs.blogger)
    implementation(projects.libs.uikit)
}