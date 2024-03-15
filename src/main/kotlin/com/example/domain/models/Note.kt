package com.example.domain.models

import com.example.domain.helpers.responses.NoteResponse
import java.util.UUID

class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val message: String
)

fun Note.toNoteResponse(): NoteResponse {
    return NoteResponse(
        id = id.toString(),
        title = title,
        message = message,
    )
}