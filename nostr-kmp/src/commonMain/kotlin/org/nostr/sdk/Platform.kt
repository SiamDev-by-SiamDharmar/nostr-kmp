package org.nostr.sdk


interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

