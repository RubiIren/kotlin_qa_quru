package backend.users

import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.models.users.UpdateRequest
import backend.api.models.users.defaultUser
import backend.controllers.Controllers
import io.kotest.matchers.equals.shouldBeEqual
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
}