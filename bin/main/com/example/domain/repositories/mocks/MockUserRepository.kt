package com.example.domain.repositories.mocks

import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import java.util.*

class MockUserRepository : UserRepository {
    private val users = mutableListOf<User>()
    override suspend fun findById(userId: UUID): User? {
        return users.find { it.id == userId }
    }

    override suspend fun findByEmail(email: String): User? {
        return users.find { it.email == email }
    }

    override suspend fun save(user: User): User {
        users.add(user)
        return user
    }

    override suspend fun update(user: User): User {
        val existingUser = users.find { it.id == user.id }
        existingUser?.apply {
            name = user.name
            email = user.email
        }
        return existingUser ?: throw IllegalArgumentException("User not found")
    }

    override suspend fun delete(userId: UUID) {
        users.removeIf { it.id == userId }
    }

}
