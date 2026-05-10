package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import frontend.helpers.Wrappers.Companion.byTestGroup
import frontend.helpers.Wrappers.Companion.byTestId
import io.qameta.allure.Step

class ProductsPage {
    private val txtTitle get() = element(byTestId("products-title"))
    private val listItems get() = elements(byTestGroup("product-card"))


    @Step("Получить название страницы")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Получить список товаров")
    fun getProducts(): List<ProductItem> {
        return ProductsItems(listItems).getItems()
    }
}
