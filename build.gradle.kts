plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.google.services).apply(false)
    alias(libs.plugins.crashlytics).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.ktorfit).apply(false)
    alias(libs.plugins.serialization).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}