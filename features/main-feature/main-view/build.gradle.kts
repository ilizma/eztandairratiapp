import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.android)
            implementation(libs.activity.compose)
            implementation(libs.mediarouter)
            implementation(libs.cast.framework)
            implementation(libs.glance)
            implementation(libs.glance.material3)
            implementation(libs.session)

            // region Review
            implementation(project(":review-framework"))
            // endregion

            implementation(project(":player-framework"))
        }
        commonMain.dependencies {
            implementation(libs.runtime)
            implementation(libs.foundation)
            implementation(libs.material3)
            implementation(libs.ui)
            implementation(libs.material.icons.extended)
            implementation(libs.components.resources)
            implementation(libs.ui.tooling.preview)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.navigation3.ui)
            implementation(libs.serialization.json)

            implementation(project(":view-base"))
            implementation(project(":resources"))

            implementation(project(":player-flow"))
            implementation(project(":player-view"))
            implementation(project(":player-presentation"))

            implementation(project(":schedule-flow"))
            implementation(project(":schedule-view"))
            implementation(project(":schedule-presentation"))

            implementation(project(":menu-flow"))
            implementation(project(":menu-view"))
            implementation(project(":menu-presentation"))
        }
    }
}

android {
    namespace = "com.ilizma.main.view"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    debugImplementation(libs.ui.tooling)
}
