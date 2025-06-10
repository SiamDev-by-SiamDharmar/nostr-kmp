package org.nostr.sdk

import kotlin.native.OsFamily
import kotlin.native.Platform
import kotlin.experimental.ExperimentalNativeApi

class NativePlatform(val platformInfo: String) : org.nostr.sdk.Platform {
    override val name: String = platformInfo
}

actual fun getPlatform(): org.nostr.sdk.Platform {
    return NativePlatform(getPlatformInfo())
}

@OptIn(ExperimentalNativeApi::class)
private fun getPlatformInfo(): String {
    return when (Platform.osFamily) {
        OsFamily.WINDOWS -> "Windows: ${Platform.cpuArchitecture}"
        OsFamily.LINUX -> "Linux: ${Platform.cpuArchitecture}"
        OsFamily.MACOSX -> "MacOS: ${Platform.cpuArchitecture}"
        else -> "Unknown OS (${Platform.osFamily})"
    }
}