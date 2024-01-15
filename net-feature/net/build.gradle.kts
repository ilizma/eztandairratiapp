plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
    id("org.jetbrains.kotlin.plugin.serialization")
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
    ksp(libs.ktorfit.ksp)
    implementation(libs.ktorfit)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.json.serialization)
    implementation(libs.okhttp)
}