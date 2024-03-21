package com.example.domain.models

import com.example.domain.helpers.responses.UserResponse
import java.util.*

class User(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    val password: String,
    var email: String
)

fun User.toUserResponse(): UserResponse {
    return UserResponse(
        id = id.toString(),
        name = name,
        password = password,
        email = email
    )
}