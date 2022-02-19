plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
    }

    viewBinding {
        isEnabled = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(Android.appcompat)
    implementation(AndroidUI.material)
    implementation(Rx.java)
    implementation(Rx.binding)
    implementation(Architecture.lifecycleRuntime)
    implementation(Architecture.lifecycleCommon)
    implementation(Annotation.annotation)

    // Resources
    implementation(project(":resources"))
}
