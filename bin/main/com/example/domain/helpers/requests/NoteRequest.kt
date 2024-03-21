package com.example.domain.helpers.requests

import com.example.domain.models.Note
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
class NoteRequest(
    val title: String,
    val message: String
)

fun NoteRequest.toNote(
    id: UUID = UUID.randomUUID()
): Note {
    return Note(
        id = id,
        title = title,
        message = message
    )
}