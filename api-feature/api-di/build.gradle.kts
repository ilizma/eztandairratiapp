plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.api.di"
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
    ksp(libs.ktorfit.ksp)
    implementation(libs.ktorfit)
    implementation(project(":di-base"))

    // region Api
    api(project(":api"))
    // endregion
}
