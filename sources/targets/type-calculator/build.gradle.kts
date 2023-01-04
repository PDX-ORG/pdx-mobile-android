plugins {
    id("org.lexadiky.gradle.preset.target-android")
}

android {
    namespace = "io.github.lexadiky.pdx"

    defaultConfig {
        applicationId = "io.github.lexadiky.pdx.typecalc"
    }
}

dependencies {
    implementation(projects.libs.uikit)
    implementation(projects.libs.arc)
    implementation(projects.libs.featureToggle)
    implementation(projects.libs.network)
    implementation(projects.libs.navigation)
    implementation(projects.libs.analytics)
    implementation(projects.libs.blogger)
    implementation(projects.domain.achievement)
    implementation(projects.features.settings)
    implementation(projects.features.typeChart)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
}