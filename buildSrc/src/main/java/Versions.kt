object Versions {
    object Build {
        const val compileSdk = 30
        const val minSdk = 21 // API-21 = Android 5.0 because we have not updated layouts to support 4.1.2+ (bitmap drawable and other stuff, roughly 2-4h work)
        const val targetSdk = 30
        const val versionCode = 1
        const val versionName = "1.0"
    }
}