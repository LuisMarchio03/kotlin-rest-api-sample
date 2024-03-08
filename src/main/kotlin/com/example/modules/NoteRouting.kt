package com.example.modules

import com.example.models.toNoteResponse
import com.example.helpers.requests.NoteRequest
import com.example.helpers.requests.toNote
import com.example.services.NoteService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

/**
 * Configuração das rotas relacionadas a notas para a aplicação Ktor.
 *
 * @param service O serviço de notas utilizado para manipulação de dados.
 */
fun Application.configureNoteRouting(
    service: NoteService
) {
    routing {
        // Rota para obter todas as notas
        get("/notes") {
            // Recupera todas as notas do serviço e mapeia para o formato de resposta
            val response = service.findAll().map {
                it.toNoteResponse()
            }
            // Responde com o código de status OK e a lista de notas no formato JSON
            call.respond(HttpStatusCode.OK, response)
        }

        // Rota para obter uma nota por ID
        get("/notes/{id}") {
            // Extrai o ID da requisição e tenta encontrar a nota correspondente
            val id = UUID.fromString(call.parameters["id"])
            service.findById(id)?.let { note ->
                // Se encontrada, responde com a nota no formato de resposta
                val response = note.toNoteResponse()
                call.respond(HttpStatusCode.OK, response)
            } ?: call.respond(HttpStatusCode.NotFound) // Se não encontrada, responde com Not Found
        }

        // Rota para criar uma nova nota
        post("/notes") {
            // Recebe a nota da requisição, salva no serviço e responde com a nota no formato de resposta
            val note = call.receive<NoteRequest>().toNote()
            val response = service.save(note).toNoteResponse()
            call.respond(HttpStatusCode.Created, response)
        }

        // Rota para atualizar uma nota existente por ID
        put("/notes/{id}") {
            // Extrai o ID da requisição e a nova versão da nota, salva no serviço e responde com a nota no formato de resposta
            val id = UUID.fromString(call.parameters["id"])
            val note = call.receive<NoteRequest>().toNote(id)
            val response = service.save(note).toNoteResponse()
            call.respond(HttpStatusCode.OK, response)
        }

        // Rota para excluir uma nota por ID
        delete("/notes/{id}") {
            // Extrai o ID da requisição e exclui a nota correspondente do serviço
            val id = UUID.fromString(call.parameters["id"])
            service.delete(id)
            // Responde com o código de status OK
            call.respond(HttpStatusCode.OK)
        }
    }
}
