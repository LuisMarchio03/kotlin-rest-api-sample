package com.example.domain.helpers.responses

import kotlinx.serialization.Serializable

@Serializable
class ListResponse(
    val id: String,
    val name: String,
    val position: Int,
    val boardId: String
)