import org.gradle.api.JavaVersion

object ConfigData {
    //android
    const val minSdkVersion = 21
    const val targetSdk = 35
    const val compileSdkVersion = 35
    const val versionCode = 6
    const val versionName = "3.1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val javaVersion = JavaVersion.VERSION_17
}