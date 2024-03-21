package com.example.domain.services

import com.example.domain.models.Note
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Classe de serviço para operações relacionadas a notas.
 *
 * @param database O banco de dados utilizado pelo serviço.
 */
class NoteService(database: Database) {

    // Definição da tabela 'Notes' usando Exposed
    private object Notes : Table() {
        val id = uuid("id")
        val title = varchar("title", 255)
        val message = text("message")

        // Definindo a chave primária da tabela
        override val primaryKey = PrimaryKey(id)
    }

    // Inicialização do serviço criando a tabela no banco de dados
    init {
        transaction(database) {
            SchemaUtils.create(Notes)
        }
    }

    /**
     * Função genérica para realizar consultas de banco de dados de forma suspensa usando coroutines.
     *
     * @param block Bloco de código a ser executado dentro da transação suspensa.
     * @return Resultado da transação.
     */
    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    /**
     * Recupera todas as notas da tabela 'Notes' no banco de dados.
     *
     * @return Lista de notas.
     */
    suspend fun findAll(): List<Note> = dbQuery {
        Notes.selectAll()
            .map { row -> row.toNote() }
    }

    /**
     * Encontra uma nota pelo seu ID na tabela 'Notes'.
     *
     * @param id ID da nota a ser encontrada.
     * @return A nota encontrada ou nulo se não existir.
     */
    suspend fun findById(id: UUID): Note? {
        return dbQuery {
            Notes.select { Notes.id eq id }
                .map { row -> row.toNote() }
                .singleOrNull()
        }
    }

    /**
     * Salva uma nova nota ou atualiza no banco de dados.
     *
     * @param note A nota a ser salva ou atualizada.
     * @return A nota salva ou atualizada.
     */
    suspend fun save(note: Note): Note = dbQuery {
        Notes.insertIgnore {
            it[id] = note.id
            it[title] = note.title
            it[message] = note.message
        }.let {
            Note(
                id = it[Notes.id],
                title = it[Notes.title],
                message = it[Notes.message]
            )
        }
    }

    /**
     * Exclui uma nota pelo seu ID no banco de dados.
     *
     * @param id ID da nota a ser excluída.
     */
    suspend fun delete(id: UUID) {
        dbQuery {
            Notes.deleteWhere { Notes.id.eq(id) }
        }
    }

    /**
     * Converte uma linha do banco de dados para um objeto Note.
     *
     * @return Objeto Note criado a partir da linha do banco de dados.
     */
    private fun ResultRow.toNote() = Note(
        id = this[Notes.id],
        title = this[Notes.title],
        message = this[Notes.message]
    )
}
