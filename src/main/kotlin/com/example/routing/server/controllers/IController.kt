package com.example.routing.server.controllers

import arb.kordy.routing.response.Response
import io.ktor.application.*

interface IController {
    fun get(call: ApplicationCall): Response
    fun post(call: ApplicationCall): Response
    fun put(call: ApplicationCall): Response
    fun delete(call: ApplicationCall): Response
}