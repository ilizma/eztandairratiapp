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
    implementation(Network.retrofitAdapter)
    implementation(project(":api"))
    implementation(project(":schedule-domain"))
    implementation(project(":schedule-data"))
}
