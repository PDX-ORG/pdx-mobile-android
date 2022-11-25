plugins {
    id("com.android.application").version("8.0.0-alpha08").apply(false)
    id("com.android.library").version("8.0.0-alpha08").apply(false)
    id("org.jetbrains.kotlin.android").version("1.7.20").apply(false)
    kotlin("plugin.serialization").version("1.7.20").apply(false)
}

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.14")
    }
}
