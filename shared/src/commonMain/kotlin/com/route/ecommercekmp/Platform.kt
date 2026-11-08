package com.route.ecommercekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform