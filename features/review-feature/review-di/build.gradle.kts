plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ilizma.review.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
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
    implementation(libs.koin.android)
    implementation(libs.play.review)
    implementation(libs.play.review.ktx)

    // region Review
    api(project(":review-framework"))
    api(project(":review-framework-imp"))
    // endregion

    implementation(project(":main-view"))
}
