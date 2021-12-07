package com.example.routing.response

import com.example.routing.results.GenericResponseWrapper
import com.example.routing.results.Wrapper
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend inline fun <reified T : Any> ApplicationCall.statusResponse(response: GenericResponseWrapper<T>) =
    this.respond(response.status, response.data)

suspend fun ApplicationCall.badRequest(message: String = "Bad request") =
    this.statusResponse(Wrapper(HttpStatusCode.BadRequest, message))