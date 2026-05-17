package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.components.HeaderComponent
import frontend.components.list.ProductItem
import frontend.components.list.ProductItems
import frontend.helpers.Wrappers.Companion.byTestGroup
import frontend.helpers.Wrappers.Companion.byTestId
import io.qameta.allure.Step

class MainPage {

    private val txtBannerTitle get() = element(byTestId("main-image-text"))
    private val listPopularProducts get() = ProductItems().getItems()


    @Step("Открыть главную страницу")
    fun open(): MainPage {
        Selenide.open("/")
        return this
    }

    @Step("Получить текст на баннере")
    fun getBannerTitle(): String {
        return txtBannerTitle.text
    }

    @Step("Перейти к хедеру")
    fun navigateHeader(): HeaderComponent {
        return HeaderComponent()
    }

    @Step("Получить список популярных товаров")
    fun getPopularProducts(): List<ProductItem> {
        return listPopularProducts
    }
}