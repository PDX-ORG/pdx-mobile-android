plugins {
    id("org.lexadiky.gradle.preset.library-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.lexadiky.pdx.lib.uikit"
}

dependencies {
    api(projects.libs.resources)
    api(projects.libs.arc)

    api(libs.android.core)
    api(libs.android.material)
    api(libs.android.lifecycle.runtime)
    api(libs.android.activity.compose)
    api(libs.android.compose.ui.core)
    api(libs.android.compose.ui.graphics)
    api(libs.android.compose.ui.preview)
    api(libs.android.compose.material3)

    api(libs.coil.compose.base)

    implementation(projects.libs.blogger)
}