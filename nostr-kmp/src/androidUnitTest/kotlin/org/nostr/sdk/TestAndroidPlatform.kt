package org.nostr.sdk

import kotlin.test.Test
import kotlin.test.assertTrue

class TestAndroidPlatform {

    @Test
    fun testAndroidPlatformInfo() {
        val platformInfo = getPlatform().name
        println(platformInfo)
        assertTrue(platformInfo.isNotBlank())
    }

}
