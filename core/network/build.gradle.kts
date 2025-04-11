import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildKonfig)
}

fun getLocalProperty(key: String): String {
    val properties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    }
    return properties.getProperty(key, "")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        //iosArm64(),
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
            implementation(libs.kotlin.coroutines)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)

            implementation(libs.bundles.firebase)

            implementation(libs.koin.core)

            implementation(libs.kermit)


        }
    }
}

buildkonfig {
    packageName = "org.example.recipes.core.network"
    // objectName = "YourAwesomeConfig"
    // exposeObjectWithName = "YourAwesomePublicConfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "baseUrl", getLocalProperty("baseUrl"))
        buildConfigField(FieldSpec.Type.STRING, "apiKey", getLocalProperty("key"))
        buildConfigField(FieldSpec.Type.STRING, "apiHost", getLocalProperty("host"))
        buildConfigField(FieldSpec.Type.STRING, "apiKeyHeader", getLocalProperty("keyHeader"))
        buildConfigField(FieldSpec.Type.STRING, "apiHostHeader", getLocalProperty("hostHeader"))

    }
}


android {
    namespace = "org.example.recipes.core.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
