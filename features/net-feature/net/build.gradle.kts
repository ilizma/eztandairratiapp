plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.ilizma.net"
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
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    implementation(libs.ktorfit)
    implementation(libs.ktorfit.converter.call)
    implementation(libs.ktorfit.converter.flow)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.json.serialization)
    implementation(libs.okhttp)
}