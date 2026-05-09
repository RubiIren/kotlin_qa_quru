package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class ProductsPage {
    private val txtTitle get() = element(byDataTestId("products-title"))
    private val listItems get() = elements(byDataTestGroup("product-card"))

    @Step("Получить название страницы")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Получить список товаров")
    fun getProducts(): ElementsCollection {
        return listItems
    }
}
