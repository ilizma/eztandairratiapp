plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.serialization)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.android)
            implementation(libs.activity.compose)
            implementation(libs.mediarouter)
            implementation(libs.cast.framework)

            // region Review
            implementation(project(":review-framework"))
            // endregion
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.navigation.compose)
            implementation(libs.serialization.json)

            implementation(project(":view-base"))
            implementation(project(":resources"))

            implementation(project(":player-flow"))
            implementation(project(":player-view"))
            implementation(project(":player-presentation"))

            implementation(project(":schedule-flow"))
            implementation(project(":schedule-view"))
            implementation(project(":schedule-presentation"))

            implementation(project(":menu-flow"))
            implementation(project(":menu-view"))
            implementation(project(":menu-presentation"))
        }
    }
}

android {
    namespace = "com.ilizma.main.view"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {
    debugImplementation(compose.uiTooling)
}
