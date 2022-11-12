package designpatterns.behavioral

/**
 * If some components need to work together in a way someone's output will become the others input, then we can apply chain of responsibility pattern.
 */
data class Request(val userName: String, val email: String) {
    fun isAuthorisedUser(): Boolean = true
    fun isAuthenticatedUser(): Boolean = true
}

data class Response(val answer: String)

typealias Handler = (Request) -> Response

val finalResponse: () -> (Handler) = { { request: Request -> Response("Hello ${request.userName}").also { println(it.answer) } } }

val authorise: (Handler) -> Handler = { next: Handler ->
    { request -> if (!request.isAuthorisedUser()) throw IllegalArgumentException() else next(request) }
}

val authenticate: (Handler) -> Handler = { next: Handler ->
    { request ->  if (!request.isAuthenticatedUser()) throw IllegalArgumentException() else next(request) }
}

val validate: (Handler) -> Handler = { next: Handler ->
    { request ->  if (request.userName.isEmpty() || request.email.isEmpty()) throw IllegalArgumentException("Username or Email should not be empty...") else next(request) }
}

fun main() {
    val chain: Handler = validate(authenticate(authorise(finalResponse())))
    chain(Request("", "jaons@gmail.com"))
}