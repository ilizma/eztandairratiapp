buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(Project.buildGradle)
        classpath(Project.kotlinGradle)
        classpath(Project.navigation)
        classpath(Project.googleServices)
        classpath(Project.hilt)
    }
}

allprojects {
    repositories {
        maven {
            setUrl("https://maven.google.com/")
            name = "Google"
        }
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}