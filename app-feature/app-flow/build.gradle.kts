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

    buildFeatures {
        viewBinding = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Di.hilt)
    kapt(Di.compiler)
    implementation(Android.appcompat)
    implementation(AndroidUI.material)
    implementation(Architecture.navigationFragment)
    implementation(project(":view-base"))
    implementation(project(":player-view"))
    implementation(project(":menu-view"))
    implementation(project(":schedule-flow"))
    implementation(project(":schedule-view"))
}