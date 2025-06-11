package org.nostr.sdk

import kotlin.test.Test
import kotlin.test.assertTrue

class TestNativePlatform {

    @Test
    fun testNativePlatformName() {
        val name = getPlatform().name
        println("Native OS: $name")
        assertTrue(name.isNotEmpty(), "Native platform name should not be empty")
    }

}
