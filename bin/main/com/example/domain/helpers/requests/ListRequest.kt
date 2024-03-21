package com.example.domain.helpers.requests

import com.example.domain.helpers.utils.UUIDSerializer
import com.example.domain.models.List
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ListRequest(
    val name: String,
    val position: Int,
    @Serializable(with = UUIDSerializer::class) val boardId: UUID
)

fun ListRequest.toList(
    id: UUID = UUID.randomUUID()
): List {
    return List(
        id = id,
        name = name,
        position = position,
        boardId = boardId
    )
}