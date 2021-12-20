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
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}