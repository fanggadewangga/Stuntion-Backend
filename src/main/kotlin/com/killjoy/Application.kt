package com.killjoy

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.killjoy.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "localhost") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
