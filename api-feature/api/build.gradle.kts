plugins {
    id("java-library")
    id("kotlin")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
    id("org.jetbrains.kotlin.plugin.serialization")
}

java {
    sourceCompatibility = ConfigData.javaVersion
    targetCompatibility = ConfigData.javaVersion
}

dependencies {
    implementation(libs.rxjava)
    ksp(libs.ktorfit.ksp)
    implementation(libs.ktorfit)
    implementation(libs.ktor.json.serialization)
    implementation(libs.annotation)
}