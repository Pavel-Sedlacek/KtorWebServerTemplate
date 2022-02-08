package arb.kordy.routing.response

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend fun ApplicationCall.statusResponse(response: ResponseWrapper<out Any?>) =
    this.respond(response.code, ResponseSubjectWrapper(response.subject ?: "\$internal_null"))


object GeneralResponses {
    fun success(message: String = "Action performed successfully") = ResponseWrapper(HttpStatusCode.OK, message)

    fun duplicate(message: String = "Object already exists") = ResponseWrapper(HttpStatusCode.Conflict, message)

    fun mustBeLoggedIn(message: String = "You must be logged in to perform this action") =
        ResponseWrapper(HttpStatusCode.Unauthorized, message)

    fun invalidRequest(message: String = "Request is invalid or corrupted") =
        ResponseWrapper(HttpStatusCode.BadRequest, message)

    fun error(message: String = "Something went wrong") = ResponseWrapper(HttpStatusCode.InternalServerError, message)
}

object FetchResponses {
    fun <T> entity(entity: T) = ResponseWrapper(HttpStatusCode.OK, entity)
    fun <T> notFound() = ResponseWrapper<T?>(HttpStatusCode.NotFound, null)
}

object UserResponses {
    fun noSuchUser(message: String = "No such user exists") = ResponseWrapper(HttpStatusCode.NotFound, message)
    fun invalidPassword(message: String = "No users with this username and password combination found") =
        ResponseWrapper(HttpStatusCode.NotFound, message)

    fun idsDontMatch(message: String = "provided id doesn't match expectations") =
        ResponseWrapper(HttpStatusCode.BadRequest, message)
}