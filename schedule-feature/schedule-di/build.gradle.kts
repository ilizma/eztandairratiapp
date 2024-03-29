plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.ilizma.schedule.di"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildFeatures {
        buildConfig = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(libs.rxjava)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.material)
    implementation(project(":di-base"))
    implementation(project(":view-base"))
    implementation(project(":presentation-base"))
    implementation(project(":api"))
    implementation(project(":resources"))

    // region Schedule
    api(project(":schedule-flow"))
    api(project(":schedule-flow-imp"))
    api(project(":schedule-view"))
    api(project(":schedule-presentation"))
    api(project(":schedule-presentation-imp"))
    api(project(":schedule-domain"))
    api(project(":schedule-domain-imp"))
    api(project(":schedule-data"))
    api(project(":schedule-data-imp"))
    // endregion
}
