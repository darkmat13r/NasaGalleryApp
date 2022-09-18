plugins {
    id("java-platform")
    id("maven-publish")
}

val appcompat = "1.6.0-rc01"
val activity = "1.6.0-rc01"
val constraintLayout = "2.1.4"
val archTesting = "2.0.0"
val core = "1.9.0"
val coroutines = "1.3.9"
val coroutinesTest = "1.3.9"
val espresso = "3.1.1"
val fragment = "1.5.2"
val glide = "4.13.2"
val gson = "2.9.1"
val hilt = Versions.HILT_AGP
val junit = "4.13.2"
val junitExt = "1.1.1"
val lifecycle = "2.4.1"
val material = "1.8.0-alpha01"
val mockito = "3.3.1"
val mockitoKotlin = "1.5.0"
val retrofit = "2.9.0"
val okhttp = "4.10.0"
val gsonConverter = ""
val pageIndicator = "1.3.0"
val room = "2.4.2"
val rules = "1.1.1"
val runner = "1.2.0"
val viewpager2 = "1.0.0"
val uiAutomator = "2.2.0"
val multidex = "2.0.1"
val truth = "1.1.3"

dependencies {
    constraints {
        api("${Libs.ACTIVITY_KTX}:$activity")
        api("${Libs.ARCH_TESTING}:$archTesting")
        api("${Libs.CONSTRAINT_LAYOUT}:$constraintLayout")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.COROUTINES}:$coroutines")
        api("${Libs.COROUTINES_TEST}:$coroutinesTest")
        api("${Libs.ESPRESSO_CORE}:$espresso")
        api("${Libs.ESPRESSO_CONTRIB}:$espresso")
        api("${Libs.FRAGMENT_KTX}:$fragment")
        api("${Libs.FRAGMENT_TEST}:$fragment")
        api("${Libs.GLIDE}:$glide")
        api("${Libs.GLIDE_COMPILER}:$glide")
        api("${Libs.GSON}:$gson")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.HILT_TESTING}:$hilt")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.EXT_JUNIT}:$junitExt")
        api("${Libs.TRUTH}:$truth")
        api("${Libs.KOTLIN_STDLIB}:${Versions.KOTLIN}")
        api("${Libs.LIFECYCLE_COMPILER}:$lifecycle")
        api("${Libs.LIFECYCLE_LIVE_DATA_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_KTX}:$lifecycle")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.MOCKITO_CORE}:$mockito")
        api("${Libs.MOCKITO_KOTLIN}:$mockitoKotlin")
        api("${Libs.NAVIGATION_FRAGMENT_KTX}:${Versions.NAVIGATION}")
        api("${Libs.NAVIGATION_UI_KTX}:${Versions.NAVIGATION}")
        api("${Libs.ROOM_KTX}:$room")
        api("${Libs.ROOM_RUNTIME}:$room")
        api("${Libs.ROOM_COMPILER}:$room")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.GSON_CONVERTER}:$retrofit")
        api("${Libs.INK_PAGE_INDICATOR}:$pageIndicator")
        api("${Libs.RULES}:$rules")
        api("${Libs.RUNNER}:$runner")
        api("${Libs.UI_AUTOMATOR}:$uiAutomator")
        api("${Libs.VIEWPAGER2}:$viewpager2")
        api("${Libs.MULTIDEX}:$multidex")
    }
}

publishing {
    publications {
        create<MavenPublication>("myPlatform") {
            from(components["javaPlatform"])
        }
    }
}
