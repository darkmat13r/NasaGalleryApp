import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
        // Android Build Server
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_AGP}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        // For Android Build Server

    }
}