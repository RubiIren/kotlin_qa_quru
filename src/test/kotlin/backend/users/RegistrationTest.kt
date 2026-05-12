package backend.users

import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.extension.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.controllers.Controllers
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RegistrationTest : Controllers() {

    @Test
    @DisplayName("Проверка создания пользователя")
    fun testCreateUser() {
        val randomEmail = "user${System.currentTimeMillis()}@test.com"
        val response = users.create("User",randomEmail, "123456").getAsObject()

        response.username shouldBe "User"
        response.email shouldBe randomEmail
    }

    @Test
    @DisplayName("Ошибка регистрации при пустом имени пользователя: User details cannot be null or blank")
    fun testCreateUserErrorNoUsername() {
        val response = users.create("", "user@ast.com","123456").getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "User details cannot be null or blank"
    }

    @Test
    @DisplayName("Ошибка регистрации при пустом email: User details cannot be null or blank")
    fun testCreateUserErrorNoEmail() {
        val response = users.create("User", "","123456").getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "User details cannot be null or blank"
    }

    @Test
    @DisplayName("Ошибка регистрации при пустом пароле: User details cannot be null or blank")
    fun testCreateUserErrorNoPassword() {
        val response = users.create("Users", "user@ast.com","").getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "User details cannot be null or blank"
    }

    @Test
    @DisplayName("Ошибка регистрации при ранее зарегистрированном email: Something went wrong. Please verify request.")
    fun testLoginWithNullCredentials() {
        val response = users.create(" ", "fdgfd@mail.re", "123456").getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "Something went wrong. Please verify request."
    }
}