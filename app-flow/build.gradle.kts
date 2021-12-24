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

    viewBinding {
        isEnabled = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Hilt.hilt)
    implementation(Android.appcompat)
    implementation(AndroidUI.material)
    implementation(Architecture.navigationFragment)
    implementation(project(":view-base"))
    implementation(project(":player-view"))
    implementation(project(":menu-view"))
}
