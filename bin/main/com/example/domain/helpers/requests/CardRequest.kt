package com.example.domain.helpers.requests

import com.example.domain.helpers.utils.UUIDSerializer
import com.example.domain.models.Card
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class CardRequest(
    val name: String,
    val position: Int,
    val description: String,
    @Serializable(with = UUIDSerializer::class) val boardId: UUID,
    @Serializable(with = UUIDSerializer::class) val listId: UUID
)

fun CardRequest.toCard(
    id: UUID = UUID.randomUUID()
): Card {
    return Card(
        id = id,
        title = name,
        description = description,
        position = position,
        userId = UUID.randomUUID(),
        listId = listId,
        boardId = boardId
    )
}
