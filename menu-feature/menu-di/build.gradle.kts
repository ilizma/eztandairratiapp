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
    implementation(AndroidKtx.fragment)
    implementation(Architecture.navigationFragment)
    implementation(project(":di-base"))
    implementation(project(":presentation-base"))

    // region Menu
    api(project(":menu-flow"))
    api(project(":menu-flow-imp"))
    api(project(":menu-view"))
    api(project(":menu-view-imp"))
    api(project(":menu-presentation"))
    api(project(":menu-presentation-imp"))
    // endregion
}
