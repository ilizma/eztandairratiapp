import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.activity.ktx)
        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)

            implementation(project(":view-base"))

            implementation(project(":api"))

            implementation(project(":resources"))

            // region Schedule
            api(project(":schedule-flow"))
            api(project(":schedule-flow-imp"))
            api(project(":schedule-view"))
            api(project(":schedule-presentation"))
            api(project(":schedule-presentation-imp"))
            api(project(":schedule-domain"))
            api(project(":schedule-domain-imp"))
            api(project(":schedule-data"))
            api(project(":schedule-data-imp"))
            // endregion
        }
    }
}

android {
    namespace = "com.ilizma.schedule.di"
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
