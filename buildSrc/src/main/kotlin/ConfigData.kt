import org.gradle.api.JavaVersion

object ConfigData {
    //android
    const val minSdkVersion = 21
    const val targetSdk = 34
    const val compileSdkVersion = 34
    const val versionCode = 3
    const val versionName = "2.1.1"
    const val kotlinCompilerExtensionVersion = "1.5.1"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val javaVersion = JavaVersion.VERSION_17
}