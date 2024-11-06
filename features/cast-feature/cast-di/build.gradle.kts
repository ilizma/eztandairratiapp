plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ilizma.cast.di"
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
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.coroutines)

    // resources
    implementation(project(":resources"))

    // region Cast
    api(project(":cast-flow"))
    api(project(":cast-flow-imp"))
    api(project(":cast-view"))
    api(project(":cast-framework"))
    api(project(":cast-framework-imp"))
    // endregion

    // region Player
    implementation(project(":player-framework"))
    // endregion
}
