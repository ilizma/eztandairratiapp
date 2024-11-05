plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ilizma.cast.framework.imp"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
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

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    //implementation(libs.coroutines)
    implementation(libs.appcompat)
    implementation(libs.mediarouter)
    implementation(libs.cast.framework)
    implementation(project(":cast-framework"))
    implementation(project(":cast-view"))

    // region Player
    implementation(project(":player-framework"))
    // endregion
}