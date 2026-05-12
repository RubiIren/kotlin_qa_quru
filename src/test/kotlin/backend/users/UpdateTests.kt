package backend.users

import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.models.users.UpdateRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UpdateTests : Controllers() {

    private val authHelper = AuthorizationHelper()

    @Test
    @DisplayName("Проверка изменения номера телефона пользователя")
    fun testUpdatePhoneUser() {

        val token = authHelper.getAdminToken()
        val response = users.updateUserById(token, 2, UpdateRequest(phoneNumber = "8952142")).getAsObject()

        response.phoneNumber shouldBe "8952142"
    }
}