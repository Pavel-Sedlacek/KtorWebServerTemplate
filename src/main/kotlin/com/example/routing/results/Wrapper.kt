package com.example.routing.results

import io.ktor.http.*

data class Wrapper<T>(override val status: HttpStatusCode, override val data: T) : GenericResponseWrapper<T>