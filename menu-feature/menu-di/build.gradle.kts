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
    implementation(project(":menu-flow"))
    implementation(project(":menu-flow-imp"))
    implementation(project(":menu-view"))
    implementation(project(":menu-view-imp"))
    implementation(project(":menu-presentation"))
    implementation(project(":menu-presentation-imp"))
    // endregion
}
