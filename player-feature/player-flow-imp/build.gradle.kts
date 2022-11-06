plugins {
    id("com.android.library")
    id("kotlin-android")
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
    implementation(Architecture.lifecycleCommon)
    implementation(Architecture.navigationUi)
    implementation(project(":player-flow"))
    implementation(project(":player-view"))
    implementation(project(":player-presentation"))

    implementation(project(":cast-flow"))
}
