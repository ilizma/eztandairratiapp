plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "AUDIO_URL", "\"" + Environments.debug.audioURL + "\"")
        }
        getByName("release") {
            buildConfigField("String", "AUDIO_URL", "\"" + Environments.release.audioURL + "\"")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    //android support libs
    implementation(Android.appcompat)
    implementation(AndroidUI.constraintLayout)
    implementation(AndroidKtx.core)
    implementation(Android.legacy)
    //navigation
    implementation(Architecture.navigationFragment)
    implementation(Architecture.navigationUi)
    //reactive
    implementation(Rx.binding)
    //ui
    implementation(AndroidUI.shimmer)
    implementation(AndroidUI.lottie)
    //android architecture component
    implementation(Architecture.lifecycleCommon)
    implementation(Architecture.lifecycleReactivestreams)
    //dependency injection
    kapt(Dagger.processor)
    kapt(Dagger.compiler)
    //chucker
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)

    // region implementation in presentation and app gradles
    //dependency injection
    api(Dagger.android)
    api(Dagger.support)
    //other
    api(CustomActivityOnCrash.customactivityoncrash)
    api(AndroidUI.material)
    //test
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.rules)
    androidTestImplementation(Test.core)
    androidTestImplementation(Test.extJunit)
    androidTestImplementation(Test.espresso)
    // endregion
}
