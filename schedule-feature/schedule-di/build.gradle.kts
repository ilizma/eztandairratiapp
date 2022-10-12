plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
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
    implementation(Rx.java)
    implementation(Di.hilt)
    kapt(Di.compiler)
    implementation(Architecture.navigationFragment)
    implementation(AndroidKtx.fragment)
    implementation(AndroidUI.material)
    implementation(project(":di-base"))
    implementation(project(":view-base"))
    implementation(project(":presentation-base"))
    implementation(project(":api"))
    implementation(project(":app-flow"))
    implementation(project(":resources"))

    // region Schedule
    implementation(project(":schedule-flow"))
    implementation(project(":schedule-flow-imp"))
    implementation(project(":schedule-view"))
    implementation(project(":schedule-view-imp"))
    implementation(project(":schedule-presentation"))
    implementation(project(":schedule-presentation-imp"))
    implementation(project(":schedule-domain"))
    implementation(project(":schedule-domain-imp"))
    implementation(project(":schedule-data"))
    implementation(project(":schedule-data-imp"))
    // endregion
}
