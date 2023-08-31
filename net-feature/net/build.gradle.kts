plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.net"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    implementation(libs.moshi)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rxjava3)
    implementation(libs.logging.interceptor)
}