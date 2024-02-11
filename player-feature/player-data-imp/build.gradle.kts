plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = ConfigData.javaVersion
    targetCompatibility = ConfigData.javaVersion
}

dependencies {
    implementation(libs.coroutines)
    implementation(project(":player-domain"))
    implementation(project(":player-data"))
    implementation(project(":player-framework"))
}
