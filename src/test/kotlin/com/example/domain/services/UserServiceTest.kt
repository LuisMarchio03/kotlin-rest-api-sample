package com.example.domain.services

import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import com.example.domain.services.UserService
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.*

class UserServiceTest {

    @Test
    fun `createUser should create a new user successfully`() {
        // Arrange
        val userRepository = mockk<UserRepository>()
        val userService = UserService(userRepository)
        val name = "John Doe"
        val email = "john.dow@example.com"
        val password = "password"
        val newUser = User(UUID.randomUUID(), name, email, password)

        // Mock do repositório para simular sucesso na operação de salvar
        every { runBlocking { userRepository } }


    }
}