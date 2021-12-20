plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.ilizma.eztandairratiapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug")
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    //dependency injection
    kapt(Dagger.processor)
    kapt(Dagger.compiler)
    //firebase
    implementation(Firebase.analytics)
    implementation(Firebase.messaging)
    //chucker
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)

    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
    testImplementation(project(path = ":presentation", configuration = "instrumentationTestImplementation"))
}
