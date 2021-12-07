package com.example.routing

import com.example.routing.client.configureClientRouting
import com.example.routing.server.configureServerRouting
import io.ktor.application.*

fun Application.configureRouting() {
    configureClientRouting()
    configureServerRouting()
}