package com.example

import com.example.plugins.configureMonitoring
import com.example.plugins.configureSerialization
import com.example.plugins.registerCookies
import com.example.routing.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

var entityManagerFactory: EntityManagerFactory = Persistence.createEntityManagerFactory("Test")

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureMonitoring()
        registerCookies()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
