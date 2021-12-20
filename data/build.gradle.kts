plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
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
    //dependency injection
    kapt(Dagger.processor)
    kapt(Dagger.compiler)
    //chucker
    debugImplementation(Network.chucker)
    releaseImplementation(Network.chuckerNoOp)
    //network
    api(Network.retrofit)
    api(Network.retrofitAdapter)
    api(Network.okhttpLogging)
    //parser
    api(Network.moshi)
    api(Network.moshiKotlin)
    api(Network.moshiAdapters)
    api(Network.moshiConverter)

    implementation(project(":domain"))
    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
}
