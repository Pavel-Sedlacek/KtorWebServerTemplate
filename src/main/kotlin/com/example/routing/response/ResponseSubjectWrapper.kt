package arb.kordy.routing.response

import io.ktor.http.*

data class ResponseSubjectWrapper<T>(val data: T?)

data class ResponseWrapper<T>(val code: HttpStatusCode, val subject: T)

typealias Response = ResponseWrapper<out Any?>