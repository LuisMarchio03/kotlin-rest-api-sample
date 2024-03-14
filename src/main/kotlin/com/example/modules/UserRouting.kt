package com.example.modules

import com.example.helpers.requests.UserRequest
import com.example.helpers.requests.toUser
import com.example.models.toUserResponse
import com.example.services.UserService
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
        get("/users") {
            val response = service.findAll().map {
                it.toUserResponse()
            }
            call.respond(HttpStatusCode.OK, response)
        }

        get("/users/{id}") {
            val id = UUID.fromString(call.parameters["id"])
            service.findById(id)?.let { note ->
                val response = note.toUserResponse()
                call.respond(HttpStatusCode.OK, response)
            } ?: call.respond(HttpStatusCode.NotFound)
        }

        get("/users/{email}") {
            val email = call.parameters["email"]
            email?.let { it1 ->
                service.findByMail(it1)?.let { note ->
                    val response = note.toUserResponse()
                    call.respond(HttpStatusCode.OK, response)
                }
            } ?: call.respond(HttpStatusCode.NotFound)
        }

        post("/users") {
            val email = call.parameters["email"]
            email?.let { it1 ->
                service.findByMail(it1)?.let {
                    call.respond(HttpStatusCode.BadRequest, "User already exists")
                }
            } ?: run {
                val note = call.receive<UserRequest>().toUser()
                val response = service.save(note).toUserResponse()
                call.respond(HttpStatusCode.Created, response)
            }
        }

        put("/users/{id}") {
            val id = UUID.fromString(call.parameters["id"])
            val note = call.receive<UserRequest>().toUser(id)
            val response = service.save(note).toUserResponse()
            call.respond(HttpStatusCode.OK, response)
        }
    }
}
