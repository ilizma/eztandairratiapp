plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.net.di"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"" + Environments.debug.baseURL + "\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"" + Environments.release.baseURL + "\"")
        }
    }

    buildFeatures {
        buildConfig = true
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
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(project(":di-base"))

    // region Net
    api(project(":net"))
    // endregion
}