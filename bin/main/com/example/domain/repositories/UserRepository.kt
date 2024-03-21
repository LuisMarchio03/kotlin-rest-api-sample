package com.example.domain.repositories

import com.example.domain.models.User
import java.util.*

interface UserRepository {
    suspend fun findById(userId: UUID): User?
    suspend fun findByEmail(email: String): User?
    suspend fun save(user: User): User
    suspend fun update(user: User): User
    suspend fun delete(userId: UUID)
}
