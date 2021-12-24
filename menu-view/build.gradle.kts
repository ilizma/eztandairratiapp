plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
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
    implementation(AndroidUI.constraintLayout)
    implementation(AndroidUI.material)
    implementation(project("::resources"))
    implementation(project(":view-base"))
}
