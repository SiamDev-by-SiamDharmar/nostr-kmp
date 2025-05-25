package org.nostr.sdk

import kotlinx.cinterop.*
import platform.posix.*

@OptIn(ExperimentalForeignApi::class)
object NativePlatform {
    val NATIVE_OS_NAME: String by lazy {
        memScoped {
            val uts = alloc<utsname>()
            uname(uts.ptr)
            uts.sysname.toKString()
        }
    }
}
