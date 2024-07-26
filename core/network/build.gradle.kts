import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildKonfig)
}

fun getLocalProperty(key: String): String {
    val properties = gradleLocalProperties(project.rootDir)
    return if (properties.containsKey(key)) { properties.getProperty(key) } else { "" }
    /*if (Files.exists(Paths.get("${project.rootDir}/local.properties"))) {
        // load buildkonfig.flavor if exists
        val localProperties = Properties()
        localProperties.load(FileInputStream("${project.rootDir}/local.properties"))
        if (localProperties.containsKey(key)) {
        }
    }
    return ""*/
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

            // Koin
            implementation(libs.koin.core)

        }
    }
}

buildkonfig {
    packageName = "com.example.recipes.core.network"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
