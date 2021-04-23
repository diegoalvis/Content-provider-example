plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(Versions.Build.compileSdk)
    defaultConfig {
        targetSdkVersion(Versions.Build.targetSdk)
        minSdkVersion(Versions.Build.minSdk)
        versionName = Versions.Build.versionName
        versionCode = Versions.Build.versionCode
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        // for key hashes see file "@signing-key-info.txt"
        fun com.android.build.api.dsl.SigningConfig.setAndroidDebugKeyData() {
            storePassword = "android"
            @Suppress("SpellCheckingInspection")
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        create("debugMain") {
            storeFile = file("../keystore/debug-main.keystore")
            setAndroidDebugKeyData()
        }
        create("debugOther") {
            storeFile = file("../keystore/debug-other.keystore")
            setAndroidDebugKeyData()
        }
    }
    flavorDimensions("appType")
    productFlavors {
        create("sourceApp") { // Normal variant, can be both BNR server build or prod-server build
            dimension = "appType" // Ignore AndroidStudio warning here
            signingConfig = signingConfigs.getByName("debugMain")
            applicationIdSuffix = ".sourceApp"
        }
        create("sameSig") { // Normal variant, can be both BNR server build or prod-server build
            dimension = "appType" // Ignore AndroidStudio warning here
            signingConfig = signingConfigs.getByName("debugMain")
            applicationIdSuffix = ".same"
        }
        create("otherSig") { // Normal variant, can be both BNR server build or prod-server build
            dimension = "appType" // Ignore AndroidStudio warning here
            signingConfig = signingConfigs.getByName("debugOther")
            applicationIdSuffix = ".other"
        }
    }
    buildTypes {
        named("debug") {
            signingConfig = null // This allows us to overwrite in productFlavor
        }
        named("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation(Libs.Kotlin.stdlib)
}
