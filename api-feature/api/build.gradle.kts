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
    implementation(libs.moshi)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rxjava3)
}