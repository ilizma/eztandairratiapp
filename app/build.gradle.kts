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
    implementation(CustomActivityOnCrash.customactivityoncrash)
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)

    // api
    implementation(project(":api-di"))
    implementation(project(":api"))

    // net
    implementation(project(":net-di"))
    implementation(project(":net"))

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
    implementation(project(":app-flow"))
    implementation(project(":app-flow-imp"))
    implementation(project(":app-view"))
    // endregion

    // region Error Management
    implementation(project(":error-management-di"))
    implementation(project(":error-management-view"))
    implementation(project(":error-management-view-imp"))
    // endregion

    // region Player
    implementation(project(":player-di"))
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

    // region Schedule
    implementation(project(":schedule-di"))
    implementation(project(":schedule-flow"))
    implementation(project(":schedule-flow-imp"))
    implementation(project(":schedule-view"))
    implementation(project(":schedule-view-imp"))
    implementation(project(":schedule-presentation"))
    implementation(project(":schedule-presentation-imp"))
    implementation(project(":schedule-domain"))
    implementation(project(":schedule-domain-imp"))
    implementation(project(":schedule-data"))
    implementation(project(":schedule-data-imp"))
    // endregion

    // region Menu
    implementation(project(":menu-di"))
    implementation(project(":menu-flow"))
    implementation(project(":menu-flow-imp"))
    implementation(project(":menu-view"))
    implementation(project(":menu-view-imp"))
    implementation(project(":menu-presentation"))
    implementation(project(":menu-presentation-imp"))
    // endregion
}
