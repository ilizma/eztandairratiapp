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

    buildTypes {
        getByName("debug")
        getByName("release")
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    //android support libs
    implementation(Android.appcompat)
    implementation(AndroidUI.constraintLayout)
    //ui
    implementation(AndroidUI.material)
    implementation(AndroidUI.shimmer)
}
