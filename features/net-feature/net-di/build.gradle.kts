import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.buildkonfig)
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
        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.ktorfit)

            // region Net
            api(project(":net"))
            // endregion
        }
    }
}

android {
    namespace = "com.ilizma.net.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

}

project.extra.set("buildkonfig.flavor", currentBuildVariant())

private fun Project.currentBuildVariant(): String {
    val variants = setOf("dev", "release")
    return getAndroidBuildVariantOrNull()
        ?: System.getenv()["VARIANT"]
            .toString()
            .takeIf { it in variants } ?: "dev"
}

private fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("dev", "release")
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(taskRequestsStr)
    val variant = if (matcher.find()) matcher.group(1).lowercase() else null
    return if (variant in variants) {
        variant
    } else {
        null
    }
}

buildkonfig {
    packageName = "com.ilizma.net.di"

    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", Environments.release.baseURL)
    }

    defaultConfigs("dev") {
        buildConfigField(STRING, "BASE_URL", Environments.debug.baseURL)
    }
}

dependencies {
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
}