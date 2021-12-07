package com.example.routing.results

import io.ktor.http.*

interface GenericResponseWrapper<T> {
    val status: HttpStatusCode
    val data: T
}