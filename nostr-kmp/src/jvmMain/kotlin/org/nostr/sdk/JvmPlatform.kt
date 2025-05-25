package org.nostr.sdk

object JvmPlatform {
    val JVM_OS_NAME: String by lazy {
        System.getProperty("os.name") ?: "Unknown"
    }
}
