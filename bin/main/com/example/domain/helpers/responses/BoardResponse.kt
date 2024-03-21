package com.example.domain.helpers.responses

import kotlinx.serialization.Serializable

@Serializable
class BoardResponse(
    val id: String,
    val name: String,
    val description: String,
    val userId: String,
)