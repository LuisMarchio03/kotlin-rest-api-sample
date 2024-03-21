package com.example.domain.repositories

import com.example.domain.models.Board
import java.util.UUID

interface BoardRepository {
    suspend fun findByNameAndUser(
        name: String,
        userId: UUID
    ): Board?

    suspend fun findById(boardId: UUID): Board?
    suspend fun findAll(): List<Board>?
    suspend fun save(board: Board): Board
    suspend fun update(board: Board): Board
    suspend fun delete (boardId: UUID)

}