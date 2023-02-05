package com.killjoy

import com.killjoy.di.databaseModule
import com.killjoy.di.routeModule
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.logger.Level
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger


fun main() {
    embeddedServer(
        Netty,
        port = System.getenv("PORT").toInt()
    ) {
        install(Koin) {
            slf4jLogger(Level.ERROR)
            modules(listOf(databaseModule, routeModule))
        }
        install(Locations)
    }.start(wait = true)
}
