package database.user

import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class ExposedHelperUser {
    private val jdbcUrl = "jdbc:postgresql://localhost:5432/playground"
    private val username: String = "postgres"
    private val password: String = "postgres"

    fun getAllUsersExposed(): List<UsersExposed> {
        val database = Database.connect(
            url = jdbcUrl,
            driver = "org.postgresql.Driver",
            user = username,
            password = password
        )

        return transaction(database) {
            UsersEntity
                .selectAll()
                .map { UsersEntity.toModel(it) }
        }.also { database.connector().close() }
    }
}

object UsersEntity : IntIdTable("table_users") {
    var Username = varchar("username", 100)
    var Email = varchar("email", 100)
    var Password = varchar("password", 100)
    var PhoneNumber = varchar("phoneNumber", 100)
    var CreatedAt = long("createdAt")
}

fun UsersEntity.toModel(resultRow: ResultRow) = UsersExposed(
    id = resultRow[id].value,
    username = resultRow[UsersEntity.Username],
    password = resultRow[UsersEntity.Password],
    email = resultRow[UsersEntity.Email],
    phoneNumber = resultRow[UsersEntity.PhoneNumber],
    createdAt = resultRow[UsersEntity.CreatedAt]
)

data class UsersExposed(
    var id: Int,
    var username: String,
    var password: String,
    var email: String,
    var phoneNumber: String?,
    var createdAt: Long
)