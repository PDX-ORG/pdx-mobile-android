plugins {
    id("com.android.application").version("8.0.0-alpha09").apply(false)
    id("com.android.library").version("8.0.0-alpha09").apply(false)
    id("org.jetbrains.kotlin.android").version("1.7.20").apply(false)
    kotlin("plugin.serialization").version("1.7.20").apply(false)

    id("io.github.lexadiky.pdx.plugin.eve").apply(false)
}

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.14")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
    }
}
