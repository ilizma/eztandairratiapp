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

    // region Player
    implementation(project(":player-flow"))
    implementation(project(":player-flow-imp"))
    implementation(project(":player-view"))
    implementation(project(":player-view-imp"))
    implementation(project(":player-presentation"))
    implementation(project(":player-presentation-imp"))
    implementation(project(":player-domain"))
    implementation(project(":player-domain-imp"))
    implementation(project(":player-data"))
    implementation(project(":player-data-imp"))
    implementation(project(":player-framework"))
    implementation(project(":player-framework-imp"))
    // endregion

    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
    testImplementation(project(path = ":presentation", configuration = "instrumentationTestImplementation"))
}
