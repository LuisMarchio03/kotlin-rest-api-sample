package com.example.services

import com.example.models.User
import com.example.plugins.UserService
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserService(database: Database) {
    // Definição da tabela 'User' usando Exposed
    private object Users : Table() {
        val id = uuid("id")
        val name = varchar("name", 180)
        val age = integer("age")
        val email = varchar("email", 255)

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

    // Find by ID
    suspend fun findById(id: UUID): User? {
        return dbQuery {
            Users.select { Users.id eq id }
                .map { row -> row.toUser() }
                .singleOrNull()
        }
    }

    // Find by Mail
    suspend fun findByMail(mail: String): User? {
        return dbQuery {
            Users.select { Users.email eq mail }
                .map { row -> row.toUser() }
                .singleOrNull()
        }
    }

    // Save
    suspend fun save(user: User): User = dbQuery {
        Users.insertIgnore {
            it[id] = user.id
            it[name] = user.name
            it[email] = user.email
            it[age] = user.age
        }.let {
            User (
                id = it[Users.id],
                name = it[Users.name],
                email = it[Users.email],
                age = it [Users.age]
            )
        }
    }

    private fun ResultRow.toUser() = User(
        id = this[Users.id],
        name = this[Users.name],
        age = this[Users.age],
        email = this[Users.email]
    )
}
