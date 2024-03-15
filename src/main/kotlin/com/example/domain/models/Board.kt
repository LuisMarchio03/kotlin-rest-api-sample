package com.example.domain.models

import com.example.domain.helpers.responses.BoardResponse
import java.util.*

class Board(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val userId: UUID,
) {
    val createdBy: User? = null
}

fun Board.toBoardResponse(): BoardResponse {
    return BoardResponse(
        id = id.toString(),
        name = name,
        description = description,
        userId = userId.toString(),
    )
}