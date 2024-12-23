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
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.android)
            implementation(libs.appcompat)
            implementation(libs.media)
            implementation(libs.session)
            implementation(libs.exoplayer)
            implementation(libs.core.ktx)
            implementation(libs.activity.ktx)
            implementation(libs.lifecycle.common)
            implementation(libs.annotation)

            // region Cast
            implementation(project(":cast-flow"))
            implementation(project(":cast-framework"))
            // endregion

            implementation(project(":main-view"))
        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)

            // Resources
            implementation(project(":resources"))

            // region Player
            api(project(":player-flow"))
            api(project(":player-flow-imp"))
            api(project(":player-view"))
            api(project(":player-presentation"))
            api(project(":player-presentation-imp"))
            api(project(":player-domain"))
            api(project(":player-domain-imp"))
            api(project(":player-data"))
            api(project(":player-data-imp"))
            api(project(":player-framework"))
            api(project(":player-framework-imp"))
            // endregion

            implementation(project(":main-view"))
        }
    }
}

android {
    namespace = "com.ilizma.player.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

}
