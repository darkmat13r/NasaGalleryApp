plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.nasa.gallery.mobile"
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        applicationId = "com.nasa.gallery.mobile"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName
        multiDexEnabled = true
        testInstrumentationRunner = "com.nasa.mobile.CustomTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))

    implementation(Libs.CORE_KTX)

    //Shimmer
    implementation(Libs.SHIMMER)

    //Networking
    implementation(Libs.GSON_CONVERTER)
    implementation(Libs.RETROFIT)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)

    // Multidex
    implementation(Libs.MULTIDEX)

    // UI
    implementation(Libs.ACTIVITY_KTX)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.MATERIAL)
    implementation(Libs.INK_PAGE_INDICATOR)

    // Architecture Components
    implementation(Libs.LIFECYCLE_LIVE_DATA_KTX)
    implementation(Libs.LIFECYCLE_RUNTIME_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)
    testImplementation(Libs.ARCH_TESTING)
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Glide
    implementation(Libs.GLIDE)
    kapt(Libs.GLIDE_COMPILER)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // Instrumentation tests
    androidTestImplementation(Libs.ESPRESSO_CORE)
    androidTestImplementation(Libs.ESPRESSO_CONTRIB)
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.RUNNER)
    androidTestImplementation(Libs.RULES)
    androidTestImplementation(Libs.FRAGMENT_TEST)
    androidTestImplementation(Libs.TRUTH)
    androidTestImplementation(Libs.COROUTINES_TEST)
    debugImplementation(Libs.FRAGMENT_TEST)

    // Local unit tests
    testImplementation(Libs.JUNIT)
    testImplementation(Libs.MOCKITO_CORE)
    testImplementation(Libs.MOCKITO_KOTLIN)
    testImplementation(Libs.TRUTH)
    testImplementation(Libs.COROUTINES_TEST)

    // Solve conflicts with gson. DataBinding is using an old version.
    implementation(Libs.GSON)

}