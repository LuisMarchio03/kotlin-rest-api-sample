package com.example.modules

import com.example.domain.helpers.requests.UserRequest
import com.example.domain.helpers.requests.toUser
import com.example.domain.models.toUserResponse
import com.example.domain.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureUserRouting(
    service: UserService
) {
    routing {
        get("/users/{id}") {
            val id = UUID.fromString(call.parameters["id"])
            val response = service.getUserById(id)?.let { user ->
                val response = user.toUserResponse()
                call.respond(HttpStatusCode.OK, response)
            } ?: call.respond(HttpStatusCode.NotFound) // Se não encontrada, responde com Not Found
        }

        post("/users") {
            val user = call.receive<UserRequest>().toUser()
            val response = service.createUser(user.name, user.email, user.password).toUserResponse()
            call.respond(HttpStatusCode.Created, response)
        }
    }
}

