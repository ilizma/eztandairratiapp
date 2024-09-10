plugins {
    id("java-library")
    id("kotlin")
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit")
}

java {
    sourceCompatibility = ConfigData.javaVersion
    targetCompatibility = ConfigData.javaVersion
}

dependencies {
    //implementation(libs.coroutines)
    implementation(libs.ktorfit)
    implementation(project(":api"))
    implementation(project(":schedule-domain"))
    implementation(project(":schedule-data"))
}
