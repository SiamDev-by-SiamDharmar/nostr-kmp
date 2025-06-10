package org.nostr.sdk

import platform.UIKit.UIDevice

class IosPlatform : Platform {
    override val name: String = "${UIDevice.currentDevice.systemName} ${UIDevice.currentDevice.systemVersion}"
}

actual fun getPlatform(): Platform {
    return IosPlatform()
}