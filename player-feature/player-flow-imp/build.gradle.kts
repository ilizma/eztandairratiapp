plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.ilizma.player.flow.imp"
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
    implementation(project(":player-flow"))
    implementation(project(":player-view"))
    implementation(project(":player-presentation"))

    implementation(project(":cast-flow"))
}
