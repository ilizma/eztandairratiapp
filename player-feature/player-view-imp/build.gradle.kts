plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.player.view.imp"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
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
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //implementation(libs.coroutines)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.common)
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(project(":resources"))
    
    implementation(project(":view-base"))

    implementation(project(":player-view"))
    implementation(project(":player-presentation"))

    implementation(project(":cast-view"))
}
