@file:OptIn(ExperimentalWasmDsl::class)

import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import java.util.Locale

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    id("signing")
}

group = "org.nostr.sdk"
version = "0.1.0"


enum class OsName { WINDOWS, MAC, LINUX, UNKNOWN }
enum class OsArch { X86_32, X86_64, ARM64, UNKNOWN }
data class OsType(val name: OsName, val arch: OsArch)

val currentOsType = run {
    val gradleOs = OperatingSystem.current()
    val osName = when {
        gradleOs.isMacOsX -> OsName.MAC
        gradleOs.isWindows -> OsName.WINDOWS
        gradleOs.isLinux -> OsName.LINUX
        else -> OsName.UNKNOWN
    }

    val osArch = when (providers.systemProperty("sun.arch.data.model").get()) {
        "32" -> OsArch.X86_32
        "64" -> when (providers.systemProperty("os.arch").get().lowercase(Locale.getDefault())) {
            "aarch64" -> OsArch.ARM64
            else -> OsArch.X86_64
        }
        else -> OsArch.UNKNOWN
    }

    OsType(osName, osArch)
}


kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    androidTarget {
        publishLibraryVariants("release", "debug")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

/*
    when (currentOsType.name) {
        OsName.MAC -> {
            iosX64()
            iosArm64()
            iosSimulatorArm64()
        }
        OsName.LINUX -> linuxX64()
        OsName.WINDOWS -> mingwX64()
        else -> println("Unsupported Platform: ${currentOsType.name}")
    }
*/

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()
    mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib.common)
                implementation(libs.ktor.client.websockets)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }


        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

//        val wasmJsMain by getting {
//            dependencies {
//                // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-browser
//                //implementation("org.jetbrains.kotlinx:kotlinx-browser:0.3")
//            }
//        }

    }


    wasmJs {
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(project.rootDir.path)
                    }
                }
            }
        }
        binaries.executable()
        nodejs()
    }

}


android {
    namespace = "org.nostr.sdk"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    //signAllPublications()

    coordinates(group.toString(), "nostr-kmp", version.toString())

    pom {
        name = "nostr-kmp"
        description = "A library."
        inceptionYear = "2025"
        url = "https://github.com/SiamDev-by-SiamDharmar/nostr-kmp/"
        licenses {
            license {
                name = "XXX"
                url = "YYY"
                distribution = "ZZZ"
            }
        }
        developers {
            developer {
                id = "XXX"
                name = "YYY"
                url = "ZZZ"
            }
        }
        scm {
            url = "XXX"
            connection = "YYY"
            developerConnection = "ZZZ"
        }
    }
}

signing {
    isRequired = false
}
