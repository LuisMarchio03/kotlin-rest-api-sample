package com.example.domain.models

import com.example.domain.helpers.responses.CardResponse
import java.util.*

class Card(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val position: Int,
    val userId: UUID,
    val listId: UUID,
    val boardId: UUID
) {
    val board: Board? = null
}

fun Card.toCardResponse(): CardResponse {
    return CardResponse(
        id = id.toString(),
        title = title,
        description = description,
        position = position,
        boardId = boardId.toString(),
        listId = listId.toString(),
        userId = userId.toString()
    )
}
