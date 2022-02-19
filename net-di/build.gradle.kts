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

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"" + Environments.debug.baseURL + "\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"" + Environments.release.baseURL + "\"")
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Hilt.hilt)
    kapt(Hilt.compiler)
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)
    implementation(Network.moshi)
    implementation(Network.moshiKotlin)
    implementation(Network.moshiAdapters)
    implementation(Network.retrofit)
    implementation(Network.okhttpLogging)
    implementation(project(":di-base"))

    // region Net
    implementation(project(":net"))
    // endregion
}