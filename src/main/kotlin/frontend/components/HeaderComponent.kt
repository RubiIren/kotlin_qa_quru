package frontend.components

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.components.popup.CartPopup
import frontend.helpers.Extensions.Companion.getFirstOrAsserted
import frontend.helpers.Wrappers.Companion.byTestGroup
import frontend.helpers.Wrappers.Companion.byTestId
import io.qameta.allure.Step
import org.openqa.selenium.By

class HeaderComponent {
    private val clickHeaderLinks get() = elements(byTestGroup("nav-link"))
    private val txtHeaderTitle get() = element(byTestId("nav-link-home"))
    private val avatarUser get() = element(By.ByClassName("avatar"))



    @Step("Нажать на раздел {name} в хедере")
    fun clickLink(name: String): HeaderComponent {
        clickHeaderLinks.getFirstOrAsserted(name).click()
        return this
    }

    @Step("Получить текст на логотипе")
    fun getHeaderTitle(): String {
        return txtHeaderTitle.text
    }

    @Step("Получить список ссылок в хедере")
    fun getHeaderLinks(): List<String> {
        return clickHeaderLinks.map { it.text }
    }

    @Step("Проверить наличие аватара")
    fun checkAvatarUser(): Boolean {
        return avatarUser.isDisplayed
    }

    @Step("Получить попап корзины")
    fun navigateCartPopup(): CartPopup {
        return CartPopup()
    }
}