plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
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
    //implementation(libs.coroutines)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.activity.ktx)
    implementation(libs.material)
    implementation(libs.settings)
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
