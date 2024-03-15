package com.example.domain.helpers.responses

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class CardResponse(
    val id: String,
    val title: String,
    val description: String,
    val position: Int,
    val userId: String,
    val listId: String,
    val boardId: String
)