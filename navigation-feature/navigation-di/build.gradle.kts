plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.ilizma.navigation.di"
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
    implementation(libs.rxjava)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.fragment.ktx)
    implementation(libs.material)
    implementation(project(":di-base"))
    implementation(project(":view-base"))
    implementation(project(":presentation-base"))
    implementation(project(":api"))
    implementation(project(":resources"))

    // region Navigation
    api(project(":navigation-view"))
    api(project(":navigation-presentation"))
    api(project(":navigation-presentation-imp"))
    api(project(":navigation-domain"))
    api(project(":navigation-domain-imp"))
    // endregion

    // region Navigation
    implementation(project(":schedule-data"))
    implementation(project(":schedule-data-imp"))
    // endregion
}
