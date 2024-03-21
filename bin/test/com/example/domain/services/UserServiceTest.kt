package com.example.domain.services

import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import com.example.domain.repositories.mocks.MockUserRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class UserServiceTest {

    @Test
    fun test_createUser_validParameters() {
        // Arrange
        val userRepository: UserRepository = MockUserRepository()
        val userService = UserService(userRepository)
        val name = "John Doe"
        val email = "john.doe@example.com"
        val password = "password123"

        // Act
        val result: User = runBlocking {
            userService.createUser(name, email, password)
        }

        // Assert
        assertNotNull(result)
        assertEquals(name, result.name)
//        assertEquals(email, result.email)
//        assertEquals(password, result.password)
    }
}