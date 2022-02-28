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
        getByName("test").java.srcDirs("src/test/kotlin")
    }

}

dependencies {
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)
    implementation(Rx.java)
    implementation(Rx.kotlin)
    implementation(Architecture.lifecycleViewModel)
    implementation(Architecture.lifecycleLivedata)
    implementation(project(":presentation-base"))
    implementation(project(":schedule-presentation"))
    implementation(project(":schedule-domain"))
}