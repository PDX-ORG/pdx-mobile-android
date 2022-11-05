enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
pluginManagement {

}

rootProject.name = "lexadky-template-android-build-preset"

include(":preset")
include(":module-generator")
