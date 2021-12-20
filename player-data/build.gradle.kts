plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    //dependency injection
    implementation(Dagger.android)
    implementation(Dagger.support)
}