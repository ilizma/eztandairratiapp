plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")
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
    implementation(Di.hilt)
    kapt(Di.compiler)
    implementation(Android.v4)
    implementation(platform(Firebase.bom))
    implementation(Firebase.crashlytics)
    implementation(Firebase.analytics)
    implementation(Firebase.messaging)
    implementation(Play.review)
    implementation(Play.reviewKtx)
    implementation(CustomActivityOnCrash.customactivityoncrash)
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)

    // api
    implementation(project(":api-di"))

    // net
    implementation(project(":net-di"))

    // di
    implementation(project(":di-base"))

    // View
    implementation(project(":view-base"))

    // Presentation
    implementation(project(":presentation-base"))

    // Resources
    implementation(project(":resources"))

    // region App
    implementation(project(":app-di"))
    // endregion

    // region Error Management
    implementation(project(":error-management-di"))
    // endregion

    // region Player
    implementation(project(":player-di"))
    // endregion

    // region Schedule
    implementation(project(":schedule-di"))
    // endregion

    // region Menu
    implementation(project(":menu-di"))
    // endregion

    // region Review
    implementation(project(":review-di"))
    // endregion
}
