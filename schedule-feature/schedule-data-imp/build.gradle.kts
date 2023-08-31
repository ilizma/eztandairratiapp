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
    implementation(libs.retrofit.adapter.rxjava3)
    implementation(project(":api"))
    implementation(project(":schedule-domain"))
    implementation(project(":schedule-data"))
    implementation(project(":navigation-domain"))
}
