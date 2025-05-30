plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ilizma.cast.framework.imp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "AUDIO_URL", "\"" + Environments.debug.audioURL + "\"")
        }
        getByName("release") {
            buildConfigField("String", "AUDIO_URL", "\"" + Environments.release.audioURL + "\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.appcompat)
    implementation(libs.mediarouter)
    implementation(libs.cast.framework)
    implementation(project(":cast-framework"))
    implementation(project(":cast-view"))

    // region Player
    implementation(project(":player-framework"))
    // endregion
}