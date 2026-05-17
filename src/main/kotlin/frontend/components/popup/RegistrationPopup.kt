package frontend.components.popup

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import frontend.helpers.Wrappers.Companion.byTestId
import io.qameta.allure.Step

class RegistrationPopup {
    private val linkAuth get() = element(byTestId("create-login"))
    private val inputUsername get() = element(byTestId("create-username")).find(shadowCss("input"))
    private val inputEmail get() = element(byTestId("create-email")).find(shadowCss("input"))
    private val inputPassword get() = element(byTestId("create-password")).find(shadowCss("input"))
    private val txtError get() = element(byTestId("create-error"))
    private val btnCreate get() = element(byTestId("create-submit"))

    @Step("Нажать на ссылку для перехода к авторизации")
    fun clickLink(): RegistrationPopup {
        linkAuth.click()
        return this
    }

    @Step("Заполнить поле имя пользователя")
    fun inputUsername(username: String): RegistrationPopup {
        inputUsername.value = username
        return this
    }

    @Step("Заполнить поле email")
    fun inputEmail(email: String): RegistrationPopup {
        inputEmail.value = email
        return this
    }

    @Step("Заполнить поле пароль")
    fun inputPassword(password: String): RegistrationPopup {
        inputPassword.value = password
        return this
    }

    @Step("Получить текст ошибки")
    fun getErrorText(): String {
        txtError.shouldBe(visible)
        return txtError.text
    }

    @Step("Нажать на кнопку Create User")
    fun clickCreateUser(): RegistrationPopup {
        btnCreate.click()
        return this
    }
}