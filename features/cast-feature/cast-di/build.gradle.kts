import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ilizma.cast.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.coroutines)

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
