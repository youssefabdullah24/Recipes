import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}


kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "network"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            // Ktor Client
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {

            // Ktor Client
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {

            // Coroutines
            implementation(libs.kotlin.coroutines)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)

        }
    }
}

android {
    namespace = "org.example.recipes.core.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
