plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdk
        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug")
        getByName("release")
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

val unitTestImplementation: Configuration by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

dependencies {
    //rxJava
    implementation(Rx.java)
    //dependency injection
    kapt(Dagger.processor)
    kapt(Dagger.compiler)

    // region implementations in all gradles
    api(Project.kotlin)
    api(Rx.kotlin)
    //dependency injection
    api(Dagger.dagger)
    //annotation
    api(Annotation.annotation)
    //other
    api(Timber.timber)
    //UnitTest
    testImplementation(Test.junit)
    testImplementation(Test.mockitoCore)
    testImplementation(Test.mockitoInline)
    testImplementation(Test.mockitoKotlin)
    // endregion
}
