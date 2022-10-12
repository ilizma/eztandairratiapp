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
    implementation(Architecture.navigationFragment)
    implementation(Architecture.lifecycleCommon)
    implementation(Annotation.annotation)

    // di
    implementation(project(":di-base"))

    // Presentation
    implementation(project(":presentation-base"))

    // Resources
    implementation(project(":resources"))

    // region Player
    api(project(":player-flow"))
    api(project(":player-flow-imp"))
    api(project(":player-view"))
    api(project(":player-view-imp"))
    api(project(":player-presentation"))
    api(project(":player-presentation-imp"))
    api(project(":player-domain"))
    api(project(":player-domain-imp"))
    api(project(":player-data"))
    api(project(":player-data-imp"))
    api(project(":player-framework"))
    api(project(":player-framework-imp"))
    // endregion
}
