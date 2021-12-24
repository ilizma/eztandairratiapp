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
    implementation(Hilt.hilt)
    implementation(Android.appcompat)
    implementation(Architecture.navigationUi)
    implementation(Architecture.navigationFragment)
    implementation(project(":app-flow"))
    implementation(project(":app-view"))
}
