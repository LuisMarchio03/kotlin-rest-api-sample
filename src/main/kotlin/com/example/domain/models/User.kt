package com.example.domain.models

import com.example.domain.helpers.responses.UserResponse
import java.util.*

class User(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val age: Int,
    val email: String
)

fun User.toUserResponse(): UserResponse {
    return UserResponse(
        id = id.toString(),
        name = name,
        age = age,
        email = email
    )
}