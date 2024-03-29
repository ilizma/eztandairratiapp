import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.safeargs) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.junit5) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = ConfigData.javaVersion.toString()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}