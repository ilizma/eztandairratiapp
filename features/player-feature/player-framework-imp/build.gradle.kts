plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.buildconfig)
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
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.android)
            implementation(libs.appcompat)
            implementation(libs.media)
            implementation(libs.session)
            implementation(libs.exoplayer)
            implementation(libs.core.ktx)
        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(project(":resources"))
            implementation(project(":player-framework"))
        }
        iosMain.dependencies {
            implementation(libs.coroutines)
        }
    }
}

android {
    namespace = "com.ilizma.player.framework.imp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

}

buildConfig {
    buildConfigField("String", "AUDIO_URL", "\"" + Environments.release.baseURL + "\"")
}