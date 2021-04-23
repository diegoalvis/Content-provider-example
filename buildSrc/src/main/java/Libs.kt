// Example of how to configure android from kotlin / write custom plugin: https://quickbirdstudios.com/blog/gradle-kotlin-buildsrc-plugin-android/

object Libs {
    object Kotlin {
        const val version = "1.4.32"

        // Unused, as androidStudio does not give update warnings when we use this
//        object BuildScript {
//            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
//            const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:$version"
//        }

        // https://kotlinlang.org/docs/reference/using-gradle.html
        // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib
        // Which stdlib? https://medium.com/@mbonnin/the-different-kotlin-stdlibs-explained-83d7c6bf293
        // HowTo in build.gradle.kts:   implementation(kotlin("stdlib", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))

        // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Test {
        const val junit = "junit:junit:4.13.1"
        const val truth = "androidx.test.ext:truth:1.3.0"
    }
}