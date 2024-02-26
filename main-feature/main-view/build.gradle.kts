plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.main.view"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ConfigData.kotlinCompilerExtensionVersion
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.voyager.navigation)
    implementation(libs.settings)
    debugImplementation(libs.ui.tooling)
    implementation(libs.mediarouter)
    implementation(libs.cast.framework)

    implementation(project(":view-base"))
    implementation(project(":resources"))

    implementation(project(":player-view"))
    implementation(project(":player-presentation"))

    implementation(project(":schedule-view"))
    implementation(project(":schedule-presentation"))

    implementation(project(":menu-view"))
    implementation(project(":menu-presentation"))

    implementation(project(":review-framework"))
}
