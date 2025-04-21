plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ilizma.cast.flow.imp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    kotlinOptions {
        jvmTarget = "21"
    }

}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.lifecycle.common)
    implementation(project(":cast-flow"))
    implementation(project(":cast-view"))
}
