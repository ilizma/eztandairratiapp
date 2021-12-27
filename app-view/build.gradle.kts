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

    viewBinding {
        isEnabled = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)
    implementation(Android.appcompat)
    implementation(AndroidUI.constraintLayout)
    implementation(AndroidUI.material)
    implementation(Architecture.navigationFragment)
    implementation(project(":view-base"))
    implementation(project(":app-flow"))
    implementation(project(":resources"))
}
