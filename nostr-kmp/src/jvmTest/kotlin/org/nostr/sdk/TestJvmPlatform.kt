package org.nostr.sdk

import kotlin.test.Test
import kotlin.test.assertTrue
import org.nostr.sdk.JVMPlatform

class TestJvmPlatform {

    @Test
    fun testJvmOsName() {
        val osName = getPlatform().name
        println("JVM OS: $osName")
        assertTrue(osName.isNotBlank(), "os.name should not be blank")
    }
}
