package com.example.domain.models

import com.example.domain.helpers.responses.ListResponse
import java.util.*

class List(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val position: Int,
    val boardId: UUID,
) {
    val board: Board? = null
}

fun List.toListResponse(): ListResponse {
    return ListResponse(
        id = id.toString(),
        name = name,
        position = position,
        boardId = boardId.toString()
    )
}