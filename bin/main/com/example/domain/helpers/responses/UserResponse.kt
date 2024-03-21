package com.example.domain.helpers.responses

import kotlinx.serialization.Serializable

@Serializable
class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
)