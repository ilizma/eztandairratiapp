plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.serialization)
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
            implementation(libs.appcompat)
            implementation(libs.lifecycle.common)
        }
        commonMain.dependencies {
            implementation(libs.serialization.json)
            implementation(libs.navigation.compose)
            implementation(project(":schedule-flow"))
            implementation(project(":schedule-view"))
            implementation(project(":schedule-presentation"))
            implementation(project(":player-flow"))
        }
    }
}

android {
    namespace = "com.ilizma.schedule.flow.imp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

}
