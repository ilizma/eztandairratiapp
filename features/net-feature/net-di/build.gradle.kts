plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
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
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    implementation(libs.ktorfit)

    // region Net
    api(project(":net"))
    // endregion
}