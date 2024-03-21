package com.example.domain.services

import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import java.util.*

class UserService(private val userRepository: UserRepository) {
    suspend fun createUser(name: String, email: String, password: String): User {
        // Verifique se o e-mail já está em uso
        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("Email already in use")
        }

        // Crie um novo usuário e salve no repositório
        val newUser = User(UUID.randomUUID(), name, email, password)
        userRepository.save(newUser)

        return newUser
    }

    suspend fun getUserById(userId: UUID): User? {
        return userRepository.findById(userId)
    }
}
