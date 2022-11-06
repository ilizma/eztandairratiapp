plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
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
    implementation(Rx.java)
    implementation(Di.hilt)
    kapt(Di.compiler)
    implementation(Architecture.navigationFragment)
    implementation(AndroidKtx.fragment)
    implementation(AndroidUI.material)
    implementation(project(":di-base"))
    implementation(project(":view-base"))
    implementation(project(":presentation-base"))
    implementation(project(":api"))
    implementation(project(":app-flow"))
    implementation(project(":resources"))

    // region Schedule
    api(project(":schedule-flow"))
    api(project(":schedule-flow-imp"))
    api(project(":schedule-view"))
    api(project(":schedule-view-imp"))
    api(project(":schedule-presentation"))
    api(project(":schedule-presentation-imp"))
    api(project(":schedule-domain"))
    api(project(":schedule-domain-imp"))
    api(project(":schedule-data"))
    api(project(":schedule-data-imp"))
    // endregion
}
