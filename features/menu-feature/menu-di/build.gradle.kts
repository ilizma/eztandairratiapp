plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
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
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.android)
            implementation(libs.activity.ktx)

        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)

            // region Menu
            api(project(":menu-flow"))
            api(project(":menu-flow-imp"))
            api(project(":menu-view"))
            api(project(":menu-presentation"))
            api(project(":menu-presentation-imp"))
            // endregion
        }
    }
}

android {
    namespace = "com.ilizma.menu.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

}
