import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
        // Android Build Server
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("com.google.gms:google-services:${Versions.GOOGLE_SERVICES}")
        classpath("androidx.benchmark:benchmark-gradle-plugin:${Versions.BENCHMARK}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Versions.FIREBASE_CRASHLYTICS}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_AGP}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}
include(
    ":app"
)