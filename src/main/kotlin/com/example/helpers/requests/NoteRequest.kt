package com.example.helpers.requests

import com.example.models.Note
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