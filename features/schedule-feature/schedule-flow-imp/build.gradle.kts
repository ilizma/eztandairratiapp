plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.ilizma.schedule.flow.imp"
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
    implementation(libs.appcompat)
    implementation(libs.lifecycle.common)
    implementation(libs.navigation.compose)
    implementation(libs.serialization.json)
    implementation(libs.settings)
    implementation(project(":schedule-flow"))
    implementation(project(":schedule-view"))
    implementation(project(":schedule-presentation"))
}
