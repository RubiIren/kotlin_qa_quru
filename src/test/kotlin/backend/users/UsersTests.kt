package backend.users

import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.models.users.UpdateRequest
import backend.api.models.users.defaultUser
import backend.controllers.Controllers
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UsersTests : Controllers() {

    @Test
    @DisplayName("Проверка изменения номера телефона пользователя")
    fun testUpdatePhoneUser() {

        val user = users.createUser(defaultUser).getAsObject()

        val updateUser = user.copy(phoneNumber = "897415894")
        val response = users.updateUserById(id = user.id, body = UpdateRequest(phoneNumber = "897415894")).getAsObject()

        response.phoneNumber shouldBe "897415894"
        updateUser shouldBeEqual response
    }

    @Test
    @DisplayName("Проверка изменения всех данных пользователя")
    fun testUpdateAllUser() {

        val user = users.createUser(defaultUser).getAsObject()

        val updateRequest = UpdateRequest(
            username = "test",
            email = "test-6975@test.com",
            password = "test123",
            phoneNumber = "897415894"
        )
        val updatedUser = users.updateUserById(id = user.id, body = updateRequest).getAsObject()

        val login = auth.login(email = updateRequest.email!!, password = updateRequest.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
        updatedUser.phoneNumber shouldBe updateRequest.phoneNumber
        updatedUser.email shouldBe updateRequest.email
        updatedUser.phoneNumber shouldBe updateRequest.phoneNumber

    }

    @Test
    @DisplayName("Проверка изменения пароля пользователя")
    fun testUpdateUserPassword() {

        val user = users.createUser(defaultUser).getAsObject()

        val updateRequest = UpdateRequest(password = "test123")
        val updatedUser = users.updateUserById(id = user.id, body = updateRequest).getAsObject()

        val login = auth.login(email = updatedUser.email, password = updateRequest.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
    }

    @Test
    @DisplayName("Проверить наличие созданного пользователя")
    fun testGetCreateUser() {
        val user = users.createUser(defaultUser).getAsObject()
        val allUsers = users.getAllUsers().getAsObject()

        allUsers shouldContain user
    }

    @Test
    @DisplayName("Проверить ограничение limit при выводе пользователей")
    fun testGetAllUsersLimit() {
        val allUsers = users.getAllUsers(limit = 5).getAsObject()

        println(allUsers)
        allUsers shouldBe 5
    }

    @Test
    @DisplayName("Проверка вывода определенных записей")
    fun testGetAllUsersOffset() {
        val allUsers = users.getAllUsers(offset = 8-10).getAsObject()

        println(allUsers)
    }
}