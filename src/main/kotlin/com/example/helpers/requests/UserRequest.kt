package com.example.helpers.requests

import com.example.models.User
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class UserRequest(
    val name: String,
    val age: Int
)

fun UserRequest.toUser(
    id: UUID = UUID.randomUUID()
): User {
    return User(
        id = id,
        name = name,
        age = age
    )
}