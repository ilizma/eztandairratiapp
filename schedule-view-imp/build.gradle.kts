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
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

}

dependencies {
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)
    implementation(AndroidKtx.core)
    implementation(AndroidUI.recyclerview)
    implementation(Architecture.lifecycleCommon)
    implementation(Architecture.lifecycleViewModel)
    implementation(project(":view-base"))
    implementation(project(":schedule-view"))
    implementation(project(":schedule-presentation"))
    implementation(project(":resources"))
}
