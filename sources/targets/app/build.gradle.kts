plugins {
    id("org.lexadiky.gradle.preset.target-android")
}

android {
    namespace = "io.github.lexadiky.pdx"

    defaultConfig {
        applicationId = "io.github.lexadiky.pdx"
    }
}

val compose_version = "1.3.0"

dependencies {
    implementation(projects.sources.libs.uikit)
    testImplementation(libs.junit4.core)
    androidTestImplementation(libs.android.test.junit4)
    androidTestImplementation(libs.android.test.espresso)
    androidTestImplementation(libs.android.compose.ui.test)
    debugImplementation(libs.android.compose.ui.tooling)
    debugImplementation(libs.android.compose.ui.test.manifest)
}