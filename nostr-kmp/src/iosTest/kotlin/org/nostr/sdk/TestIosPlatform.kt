package org.nostr.sdk

import kotlin.test.Test
import kotlin.test.assertTrue

class TestIosPlatform {

    @Test
    fun testIosDeviceInfo() {
        val platformInfo = getPlatform().name
        println(platformInfo)
        assertTrue(platformInfo.isNotBlank())
    }

}
