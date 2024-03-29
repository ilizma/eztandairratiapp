plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.menu.di"
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
    implementation(libs.fragment.ktx)
    implementation(project(":di-base"))
    implementation(project(":presentation-base"))

    // region Menu
    api(project(":menu-flow"))
    api(project(":menu-flow-imp"))
    api(project(":menu-view"))
    api(project(":menu-presentation"))
    api(project(":menu-presentation-imp"))
    // endregion
}
