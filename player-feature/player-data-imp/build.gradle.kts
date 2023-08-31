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
    implementation(project(":player-domain"))
    implementation(project(":player-data"))
    implementation(project(":player-framework"))
}
