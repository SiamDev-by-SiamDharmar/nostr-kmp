package org.nostr.sdk

import kotlin.test.Test
import kotlin.test.assertTrue

class PlatformInfoTest {

    @Test
    fun testPlatformInfoNotBlank() {
        println(Platform.info)
        assertTrue(Platform.info.isNotBlank(), "Platform info should not be blank")
    }

}