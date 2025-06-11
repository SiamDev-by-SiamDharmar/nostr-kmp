package org.nostr.sdk

import kotlin.test.Test
import kotlin.test.assertTrue

class PlatformInfoTest {

    @Test
    fun testPlatformInfo() {
        val platform = getPlatform().name
        println(platform)
        assertTrue(platform.isNotBlank())
    }

}