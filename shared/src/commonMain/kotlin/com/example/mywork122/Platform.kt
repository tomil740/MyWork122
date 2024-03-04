package com.example.mywork122

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform