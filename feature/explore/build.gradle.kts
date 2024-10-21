import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
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
        //iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "explore"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.coil.client.okhttp)
            //implementation(libs.ktor.client.okhttp)


        }

        iosMain.dependencies {
            //implementation(libs.ktor.client.darwin)

        }

        commonMain.dependencies {
            implementation(project(":navigation"))

            implementation(project(":core:data"))

            implementation(project(":core:model"))

            implementation(project(":core:ui"))

       //     implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
          //  implementation("co.touchlab:stately-concurrent-collections:2.0.6")

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.kotlin.coroutines)

            implementation(libs.coil.core)
            implementation(libs.coil.compose)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.viewModel)


            implementation(libs.viewmodel)

            implementation(libs.cupertino)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tabNavigator)
            //implementation(libs.voyager.koin)

            implementation(libs.kermit)



        }
    }
}

android {
    namespace = "org.example.recipes.feature.explore"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)

    }
}
dependencies {
    implementation(libs.androidx.foundation.android)
    implementation(libs.kotlin.coroutines)
}

