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
    implementation(Network.moshiConverter)
    implementation(Network.retrofit)
    implementation(Network.retrofitAdapter)
    implementation(Network.okhttpLogging)
}