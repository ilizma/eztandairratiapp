plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.ilizma.view.base"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ConfigData.kotlinCompilerExtensionVersion
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.rxjava)
    implementation(libs.rxbinding)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.common)
    implementation(libs.annotation)

    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    debugImplementation(libs.ui.tooling)

    // Resources
    implementation(project(":resources"))
}
