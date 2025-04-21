import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

kotlin.compilerOptions.jvmTarget.set(JvmTarget.JVM_21)

repositories {
    mavenCentral()
}