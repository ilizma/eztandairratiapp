plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.navigation.presentation.imp"
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
        getByName("test").java.srcDirs("src/test/kotlin")
    }

}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.rxjava)
    implementation(libs.rxkotlin)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(project(":presentation-base"))

    // region Navigation
    implementation(project(":navigation-presentation"))
    implementation(project(":navigation-domain"))
    // endregion
}