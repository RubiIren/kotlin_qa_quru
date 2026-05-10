package frontend

import frontend.components.AuthPopup
import frontend.components.HeaderComponent
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.collections.shouldNotContainAnyOf
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class Lesson10 : BaseUiTest() {

    @ParameterizedTest
    @ValueSource(strings = ["Products, Orders, Contact, Cart"])
    @DisplayName("Проверка наличия ссылок в хедере")
    fun testWithValueSource(link: String) {
        val listLinks = MainPage().header().getHeaderLinks()

        listLinks shouldNotContainAnyOf linkedSetOf()
    }

    @ParameterizedTest
    @CsvSource(
        // логин, пароль, ожидаемая ошибка
        "'', hXMTcNbAGSqwqhBSxQ9fDw==, 'Please enter email and password'",  // пустой email
        "wrong@test.com, hXMTcNbAGSqwqhBSxQ9fDw==, 'Invalid email or password'",  // некорректный email
        "user@autotest.com, 123 , 'Wrong password: password | 123'",       // некорректный пароль
        "user@autotest.com,'', 'Please enter email and password'",       // пустой пароль
        "wrong@test.com, wrongPass, 'Invalid email or password'"
    ) // неверные данные
    @DisplayName("Проверка ошибок в форме авторизации")
    fun testAuthErrors(email: String, password: String, expectedError: String) {
        MainPage()
            .header()
            .clickLink("Join")
        val error = AuthPopup()
            .clickLink()
            .inputEmail(email)
            .inputPassword(password)
            .clickLoginBtn()
            .getErrorText()
        expectedError shouldBe error
    }

    @Test
    @DisplayName("Проверка работы авторизации")
    fun testAuthUser(): Unit {
        MainPage()
            .header()
            .clickLink("Join")
        AuthPopup()
            .clickLink()
            .inputEmail("test@mail.com")
            .inputPassword("123")
            .clickLoginBtn()

        val avatar = HeaderComponent()
            .checkAvatarUser()

        avatar shouldBe true
    }
}