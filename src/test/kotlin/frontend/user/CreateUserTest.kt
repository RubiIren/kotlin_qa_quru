package frontend.user

import database.user.ExposedHelperUser
import database.user.JDBCHelperUser
import frontend.components.popup.RegistrationPopup
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateUserTest : BaseUiTest() {

    @Test
    @DisplayName("Проверка создания пользователя JDBCHelper")
    fun testRegUserJDBC() {
        val randomEmail = "user${System.currentTimeMillis()}@test.com"
        val jdbcClient = JDBCHelperUser()

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        RegistrationPopup()
            .inputUsername("User")
            .inputEmail(randomEmail)
            .inputPassword("12345")
            .clickCreateUser()

        val users = jdbcClient.getUsers()
        val createdUser = users.find { it.email == randomEmail }

        createdUser?.email shouldBe randomEmail
        createdUser?.username shouldBe "User"
    }

    @Test
    @DisplayName("Проверка создания пользователя ExposedHelper")
    fun testRegUserExposed() {
        val randomEmail = "user${System.currentTimeMillis()}@test.com"
        val exposedHelper = ExposedHelperUser()

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        RegistrationPopup()
            .inputUsername("User")
            .inputEmail(randomEmail)
            .inputPassword("12345")
            .clickCreateUser()

        val users = exposedHelper.getAllUsersExposed()
        val createdUser = users.find { it.email == randomEmail }

        createdUser?.email shouldBe randomEmail
        createdUser?.username shouldBe "User"
    }
}