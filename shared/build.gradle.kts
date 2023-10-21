plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
}

kotlin {
    androidTarget()
    jvm("desktop")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktorfit.lib)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.activity.compose)
                api(libs.appcompat)
                api(libs.core.ktx)
                api(kopilotLibs.kotlinx.coroutines.android)
                implementation(kopilotLibs.ktor.client.okhttp)
                // koin
                implementation(kopilotLibs.koin.android)
                implementation(kopilotLibs.koin.androidx.compose)
                implementation(libs.ksp.symbol.processing.api)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(kopilotLibs.ktor.client.darwin)
            }
        }

        val desktopMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.kkk.kopilot.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Kotlin coroutines with architecture components dependencies
    implementation (libs.lifecycle.extensions)
    implementation (libs.lifecycle.viewmodel.ktx)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.activity.ktx)
    implementation (libs.lifecycle.viewmodel.savedstate)
    implementation (libs.material3)
    implementation (libs.material.icons.extended)

    commonMainImplementation(libs.libphonenumber)

    commonMainImplementation(compose.runtime)
    commonMainImplementation(compose.foundation)
    commonMainImplementation(compose.material)

    // coroutines
    commonMainImplementation(libs.kotlinx.coroutines.core)

    // moko resources
    commonMainApi (libs.resources)
    commonMainApi (libs.resources.compose)

    // serialisation
    commonMainImplementation (libs.kotlinx.serialization.json)

    // koin
    commonMainImplementation(libs.koin.core)

    // voyager
    with(libs.voyager) {
        commonMainImplementation(navigator)
        commonMainImplementation(bottom.sheet.navigator)
        commonMainImplementation(tab.navigator)
        commonMainImplementation(transitions)
        commonMainImplementation(androidx)
        commonMainImplementation(koin)
        commonMainImplementation(hilt)
        commonMainImplementation(livedata)
        commonMainImplementation(kodein)
    }

    // ktor
    with(libs.ktor) {
        commonMainImplementation(client.mock)
        commonMainImplementation(client.negotiation)
        commonMainImplementation(client.logging)
        commonMainImplementation(serialization.kotlinx.json)
    }

    // napier
    commonMainImplementation(libs.napier)

    with(libs.ktorfit.ksp) {
        add("kspCommonMainMetadata", this)
        add("kspAndroid", this)
        add("kspAndroidTest", this)
//        add("kspIosArm64", this)
//        add("kspIosSimulatorArm64", this)
//        add("kspIosX64", this)
    }

}

multiplatformResources {
    multiplatformResourcesPackage = "com.kkk.kopilot"
    iosBaseLocalizationRegion = "fr"
}