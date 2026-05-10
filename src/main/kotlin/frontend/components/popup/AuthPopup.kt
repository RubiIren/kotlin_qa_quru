package frontend.components.popup

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import frontend.helpers.Wrappers.Companion.byTestId
import io.qameta.allure.Step

class AuthPopup {
    private val linkAuth get() = element(byTestId("create-login"))
    private val inputEmail get() = element(byTestId("login-email")).find(shadowCss("input"))
    private val inputPassword get() = element(byTestId("login-password")).find(shadowCss("input"))
    private val txtError get() = element(byTestId("login-error"))
    private val btnLogin get() = element(byTestId("login-submit"))

    @Step("Нажать на ссылку для авторизации")
    fun clickLink(): AuthPopup {
        linkAuth.click()
        return this
    }

    @Step("Заполнить поле email")
    fun inputEmail(email: String): AuthPopup {
        inputEmail.value = email
        return this
    }

    @Step("Заполнить поле пароль")
    fun inputPassword(password: String): AuthPopup {
        inputPassword.value = password
        return this
    }

    @Step("Получить текст ошибки")
    fun getErrorText(): String {
        txtError.shouldBe(visible)
        return txtError.text
    }

    @Step("Нажать на кнопку Login")
    fun clickLoginBtn(): AuthPopup {
        btnLogin.click()
        return this
    }
}