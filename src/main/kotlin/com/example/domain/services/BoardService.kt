package com.example.domain.services

import com.example.domain.models.Board
import com.example.domain.repositories.BoardRepository
import java.util.UUID

class BoardService(private val boardRepository: BoardRepository) {
    suspend fun createBoard(name: String, description: String, userId: UUID): Board {
        if (boardRepository.findByNameAndUser(name, userId) != null) {
            throw IllegalArgumentException("Board already exists")
        }

        // TODO: Boards denvem ser criados com base no usuario logado
        val newBoard = Board(UUID.randomUUID(), name, description, userId)
        boardRepository.save(newBoard)

        return newBoard
    }

    suspend fun getAllBoards(): List<Board>? {
        // TODO: Boards denvem ser listados com base no usuario logado
        return boardRepository.findAll()
    }

    suspend fun getBoardById(boardId: UUID): Board? {
        // TODO: Boards denvem ser listados com base no usuario logado
        return boardRepository.findById(boardId = boardId)
    }
}