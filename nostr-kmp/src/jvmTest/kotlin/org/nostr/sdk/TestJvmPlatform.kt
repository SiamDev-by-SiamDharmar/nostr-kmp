package org.nostr.sdk

import kotlin.test.Test
import kotlin.test.assertTrue

class TestJvmPlatform {

    @Test
    fun testJvmOsName() {
        val osName = JvmPlatform.JVM_OS_NAME
        println("JVM OS: $osName")
        assertTrue(osName.isNotBlank(), "os.name should not be blank")
    }
}
