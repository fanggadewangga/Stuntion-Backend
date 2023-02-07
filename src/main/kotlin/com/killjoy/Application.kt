package com.killjoy

import com.killjoy.di.databaseModule
import com.killjoy.di.repositoryModule
import com.killjoy.di.routeModule
import com.killjoy.plugins.configureRouting
import com.killjoy.plugins.configureSerialization
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
            modules(listOf(databaseModule, repositoryModule, routeModule))
        }
        install(Locations)
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
