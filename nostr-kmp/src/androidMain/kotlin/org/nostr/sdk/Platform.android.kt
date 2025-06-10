package org.nostr.sdk

import android.os.Build

class AndroidPlatform : org.nostr.sdk.Platform {
    override val name: String = "Android ${Build.VERSION.RELEASE} (${Build.MODEL})"
}

actual fun getPlatform(): org.nostr.sdk.Platform {
    return AndroidPlatform()
}