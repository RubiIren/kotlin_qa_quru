package frontend

import frontend.components.list.ProductItem
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Lesson11 : BaseUiTest() {

    @Test
    @DisplayName("Проверка популярных товаров и товаров в каталоге")
    fun testPopularProducts() {
        val popularProducts = MainPage()
            .open()
            .getPopularProducts()
            .first()

        MainPage()
            .navigateHeader()
            .clickLink("Products")

        val catalogProducts = ProductsPage()
            .getProducts()
            .first()

        catalogProducts.name shouldBe popularProducts.name
        catalogProducts.price shouldBe popularProducts.price
        catalogProducts.description shouldBe popularProducts.description
    }
}