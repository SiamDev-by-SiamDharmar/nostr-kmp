package org.nostr.sdk

import android.os.Build

object AndroidPlatform {
    val info: String by lazy {
        buildString {
            appendLine("OS: Android ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})")
            appendLine("Device: ${Build.MODEL ?: "Unknown"}")
            appendLine("Manufacturer: ${Build.MANUFACTURER ?: "Unknown"}")
        }
    }
}
