plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ilizma.errormanagement.view"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildFeatures {
        viewBinding = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.lottie)
    implementation(project(":view-base"))
    implementation(project(":resources"))
}
