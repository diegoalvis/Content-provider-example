// Gradle plugin Documentation: http://tools.android.com/tech-docs/new-build-system
buildscript {
    repositories {
        google() // Traverse in browser with: https://dl.google.com/dl/android/maven2/index.html
        mavenCentral()
    }
    dependencies {
        // https://dl.google.com/dl/android/maven2/index*.html
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Libs.Kotlin.version}")
    }
}

allprojects {
    repositories {
        // The order in which you list these repositories matter.
        google()
        mavenCentral()
    }
}

tasks {
    // Use named("clean") for existing task in scope
    register<Delete>("clean") {
        doLast {
            delete(rootProject.buildDir)
        }
    }
}