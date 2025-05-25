package org.nostr.sdk

import platform.UIKit.UIDevice

object IosPlatform {
    val info: String by lazy {
        buildString {
            appendLine("Device: ${UIDevice.currentDevice.name}")
            appendLine("System: ${UIDevice.currentDevice.systemName}")
            appendLine("Version: ${UIDevice.currentDevice.systemVersion}")
        }
    }
}
