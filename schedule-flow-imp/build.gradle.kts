plugins {
    id("com.android.library")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

}

dependencies {
    implementation(Android.appcompat)
    implementation(Android.activity)
    implementation(Architecture.lifecycleCommon)
    implementation(Architecture.navigationUi)
    implementation(Architecture.navigationFragment)
    implementation(project(":app-flow"))
    implementation(project(":schedule-flow"))
    implementation(project(":schedule-view"))
    implementation(project(":schedule-presentation"))
}
