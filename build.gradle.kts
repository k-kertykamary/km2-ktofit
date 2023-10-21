plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        mavenLocal()
    }

    dependencies {
        classpath(kopilotLibs.resources.generator)
        classpath(kopilotLibs.kotlin.serialization)
        classpath(kopilotLibs.ksp.gradle.plugin)
        classpath(kopilotLibs.ktorfit.gradle.plugin)
    }
}

allprojects {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}