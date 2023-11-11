plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.8.21"
    id("app.cash.sqldelight")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization)
            implementation(libs.sqldelight.runtime)
            implementation(libs.kotlinx.coroutines)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.android)
            implementation(libs.sqldelight.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.ios)
            implementation(libs.sqldelight.ios)
        }
    }
}

android {
    namespace = "dev.linmaung.atantat"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
sqldelight {
    databases {
        create("ATanTatDatabase") {
            packageName.set("dev.linmaung.atantat.kmm.shared.cache")
        }
    }
}
