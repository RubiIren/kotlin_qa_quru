package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.Selenide.open
import frontend.components.list.ProductItem
import frontend.components.list.ProductItems
import frontend.helpers.Wrappers.Companion.byTestGroup
import frontend.helpers.Wrappers.Companion.byTestId
import io.qameta.allure.Step

class ProductsPage {
    private val txtTitle get() = element(byTestId("products-title"))
    private val listItems get() = elements(byTestGroup("product-card"))
    private val listProducts get() = ProductItems().getItems()

    @Step("Открыть страницу продуктов")
    fun open(): ProductsPage {
        open("/products")
        return this
    }

    @Step("Получить название страницы")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Получить список продуктов на странице")
    fun getProducts(): ElementsCollection {
        return listItems
    }

    @Step("Получить список продуктов на странице в виде объектов")
    fun getProductsAsObjects(): List<ProductItem> {
        return listProducts
    }
}