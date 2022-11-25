plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.ftoggle"
    buildFeatures {
        compose = false
    }
}

dependencies {
    implementation(projects.libs.blogger)
    implementation(projects.libs.arc)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.remoteConfig)
}