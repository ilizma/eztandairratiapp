plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.ilizma.player.di"
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
    implementation(libs.rxjava)
    implementation(libs.appcompat)
    implementation(libs.media)
    implementation(libs.core.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.lifecycle.common)
    implementation(libs.annotation)

    // di
    implementation(project(":di-base"))

    // Presentation
    implementation(project(":presentation-base"))

    // Resources
    implementation(project(":resources"))

    // region Player
    api(project(":player-flow"))
    api(project(":player-flow-imp"))
    api(project(":player-view"))
    api(project(":player-presentation"))
    api(project(":player-presentation-imp"))
    api(project(":player-domain"))
    api(project(":player-domain-imp"))
    api(project(":player-data"))
    api(project(":player-data-imp"))
    api(project(":player-framework"))
    api(project(":player-framework-imp"))
    // endregion

    // region Cast
    implementation(project(":cast-flow"))
    // endregion
}
