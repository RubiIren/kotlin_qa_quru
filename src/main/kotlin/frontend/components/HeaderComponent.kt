package frontend.components

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.helpers.Extensions.Companion.getFirstOrAsserted
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step
import org.openqa.selenium.By

class HeaderComponent {
    private val linksHeader get() = elements(byDataTestGroup("nav-link"))
    private val txtHeaderTitle get() = element(byDataTestId("nav-link-home"))
    private val avatarUser get() = element(By.ByClassName("avatar"))



    @Step("Нажать на раздел {name} в хедере")
    fun clickLink(name: String): HeaderComponent {
        linksHeader.getFirstOrAsserted(name).click()
        return this
    }

    @Step("Получить текст на логотипе")
    fun getHeaderTitle(): String {
        return txtHeaderTitle.text
    }

    @Step("Получить список ссылок в хедере")
    fun getHeaderLinks(): List<String> {
        return linksHeader.map { it.text }
    }

    @Step("Проверить наличие аватара")
    fun checkAvatarUser(): Boolean {
        return avatarUser.isDisplayed
    }
}