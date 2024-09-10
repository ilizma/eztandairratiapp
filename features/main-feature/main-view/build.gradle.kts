plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
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

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.navigation.compose)
    implementation(libs.serialization.json)
    implementation(libs.settings)
    debugImplementation(libs.ui.tooling)
    implementation(libs.mediarouter)
    implementation(libs.cast.framework)
    //implementation(libs.ktor.json.serialization)

    implementation(project(":view-base"))
    implementation(project(":resources"))

    implementation(project(":player-flow"))
    implementation(project(":player-view"))
    implementation(project(":player-presentation"))

    implementation(project(":schedule-flow"))
    implementation(project(":schedule-view"))
    implementation(project(":schedule-presentation"))

    implementation(project(":menu-flow"))
    implementation(project(":menu-view"))
    implementation(project(":menu-presentation"))

    implementation(project(":review-framework"))
}
