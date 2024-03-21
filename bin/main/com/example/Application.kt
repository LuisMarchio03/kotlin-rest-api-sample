package com.example

import com.example.domain.repositories.UserRepository
import com.example.modules.configureNoteRouting
import com.example.domain.services.NoteService
import com.example.domain.services.UserService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    val database = Database.connect(
        url = "jdbc:h2:file:./database/db",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )
    val noteService = NoteService(database)
    configureNoteRouting(noteService)
}