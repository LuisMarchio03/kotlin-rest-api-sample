package com.example.domain.helpers.requests

import com.example.domain.models.User
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class UserRequest(
    val name: String,
    val email: String
)

fun UserRequest.toUser(
    id: UUID = UUID.randomUUID()
): User {
    return User(
        id = id,
        name = name,
        password = UUID.randomUUID().toString(),
        email = email
    )
}