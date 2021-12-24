plugins {
    id("com.android.application")
    id("kotlin-android")//kotlin-parcelize
    id("kotlin-kapt")
    id("com.google.gms.google-services")
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
    // di
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)
    //firebase
    implementation(Firebase.analytics)
    implementation(Firebase.messaging)
    //chucker
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)

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
    implementation(project(":app-view-imp"))
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

    // region Menu
    implementation(project(":menu-di"))
    implementation(project(":menu-flow"))
    implementation(project(":menu-flow-imp"))
    implementation(project(":menu-view"))
    implementation(project(":menu-view-imp"))
    implementation(project(":menu-presentation"))
    implementation(project(":menu-presentation-imp"))
    // endregion

    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))
    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
    testImplementation(project(path = ":presentation", configuration = "instrumentationTestImplementation"))
}
