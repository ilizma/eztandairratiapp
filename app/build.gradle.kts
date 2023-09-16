plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.app"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.ilizma.eztandairratiapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.session)
    implementation(libs.cast.framework)
    implementation(libs.customactivityoncrash)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

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

    // region Main
    implementation(project(":main-di"))
    // endregion

    // region Error Management
    implementation(project(":error-management-di"))
    // endregion

    // region Navigation
    implementation(project(":navigation-di"))
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

    // region Cast
    implementation(project(":cast-di"))
    // endregion

}
