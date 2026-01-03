package org.amitkundu.gamehub

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform