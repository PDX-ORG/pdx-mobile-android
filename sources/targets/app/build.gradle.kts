plugins {
    id("org.lexadiky.gradle.preset.target-android")
}

android {
    namespace = "io.github.lexadiky.pdx"

    defaultConfig {
        applicationId = "io.github.lexadiky.pdx"
    }
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.arc)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(projects.libs.analytics)
    implementation(projects.features.toolbar)
    implementation(projects.features.drawer)
    implementation(projects.features.news)
    implementation(projects.features.settings)

    testImplementation(libs.junit4.core)
    androidTestImplementation(libs.android.test.junit4)
    androidTestImplementation(libs.android.test.espresso)
    androidTestImplementation(libs.android.compose.ui.test)
    debugImplementation(libs.android.compose.ui.tooling)
    debugImplementation(libs.android.compose.ui.test.manifest)
}