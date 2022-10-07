plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "AUDIO_URL", "\"" + Environments.debug.audioURL + "\"")
        }
        getByName("release") {
            buildConfigField("String", "AUDIO_URL", "\"" + Environments.release.audioURL + "\"")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Di.hilt)
    kapt(Di.compiler)
    implementation(Rx.java)
    implementation(Android.appcompat)
    implementation(Android.v4)
    implementation(AndroidKtx.core)
    implementation(AndroidKtx.fragment)
    implementation(Architecture.lifecycleCommon)
    implementation(Annotation.annotation)

    // di
    implementation(project(":di-base"))

    // Presentation
    implementation(project(":presentation-base"))

    // Resources
    implementation(project(":resources"))

    // region Player
    implementation(project(":player-flow"))
    implementation(project(":player-flow-imp"))
    implementation(project(":player-view"))
    implementation(project(":player-view-imp"))
    implementation(project(":player-presentation"))
    implementation(project(":player-presentation-imp"))
    implementation(project(":player-domain"))
    implementation(project(":player-domain-imp"))
    implementation(project(":player-data"))
    implementation(project(":player-data-imp"))
    implementation(project(":player-framework"))
    implementation(project(":player-framework-imp"))
    // endregion
}
