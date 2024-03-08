package com.example.models

import com.example.helpers.responses.UserResponse
import java.util.*

class User(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val age: Int
)

fun User.toUserResponse(): UserResponse {
    return UserResponse(
        id = id.toString(),
        name = name,
        age = age
    )
}