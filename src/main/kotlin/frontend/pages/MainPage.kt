package frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import frontend.components.HeaderComponent
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class MainPage {

    private val txtBannerTitle get() = element(byDataTestId("main-image-text"))

    @Step("Открыть главную страницу")
    fun open() {
        Selenide.open("/")
    }

    @Step("Получить текст на баннере")
    fun getBannerTitle(): String {
        return txtBannerTitle.text
    }

    @Step("Перейти к хедеру")
    fun header(): HeaderComponent {
        return HeaderComponent()
    }
}