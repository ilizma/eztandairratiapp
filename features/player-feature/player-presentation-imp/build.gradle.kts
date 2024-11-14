plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    androidTarget()

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
            implementation(libs.lifecycle.common)

            implementation(project(":cast-framework"))
        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.lifecycle.viewmodel)

            implementation(project(":player-presentation"))
            implementation(project(":player-domain"))
        }
    }
}

android {
    namespace = "com.ilizma.player.presentation.imp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

}