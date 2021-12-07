package com.example.plugins

import io.ktor.application.*
import io.ktor.sessions.*

fun Application.registerCookies() {
    install(Sessions) {
//        cookie<XXX>("user_session")
    }
}