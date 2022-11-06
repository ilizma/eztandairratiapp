plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Di.hilt)
    kapt(Di.compiler)
    implementation(Rx.java)

    // di
    implementation(project(":di-base"))

    // resources
    implementation(project(":resources"))

    // region Cast
    api(project(":cast-flow"))
    api(project(":cast-flow-imp"))
    api(project(":cast-view"))
    api(project(":cast-framework"))
    api(project(":cast-framework-imp"))
    // endregion

    // region Player
    implementation(project(":player-framework"))
    // endregion
}
