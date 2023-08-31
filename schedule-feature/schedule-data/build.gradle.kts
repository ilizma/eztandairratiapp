plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = ConfigData.javaVersion
    targetCompatibility = ConfigData.javaVersion
}

dependencies {
    implementation(libs.rxjava)
    implementation(libs.annotation)
}