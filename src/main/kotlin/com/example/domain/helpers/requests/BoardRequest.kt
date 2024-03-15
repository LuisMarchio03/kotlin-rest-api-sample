package com.example.domain.helpers.requests

import com.example.domain.models.Board
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class BoardRequest(
    val name: String,
    val description: String,
)

fun BoardRequest.toBoard(
    id: UUID = UUID.randomUUID()
): Board {
    return Board(
        id = id,
        name = name,
        description = description,
        userId = UUID.randomUUID(),
    )
}