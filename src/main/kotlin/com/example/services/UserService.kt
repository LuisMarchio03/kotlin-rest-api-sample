package com.example.services

import com.example.models.User
import com.example.plugins.UserService
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(database: Database) {
    // Definição da tabela 'User' usando Exposed
    private object Users : Table() {
        val id = uuid("id")
        val name = varchar("name", 180)
        val age = integer("age")

        override val primaryKey = PrimaryKey(id)
    }

    // Inicialização do serviço e criando a tabela no banco de dados
    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    // Find all Users
    suspend fun findAll(): List<User> = dbQuery {
        UserService.Users.selectAll().map { row -> row.toUser() }
    }

    private fun ResultRow.toUser() = User(
        id = this[Users.id],
        name = this[Users.name],
        age = this[Users.age]
    )
}
