plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(Rx.java)
    implementation(project(":player-domain"))
    implementation(project(":player-data"))
    implementation(project(":player-framework"))
}
