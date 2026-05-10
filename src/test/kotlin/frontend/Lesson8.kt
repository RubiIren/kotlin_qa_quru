package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.ContactPage
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Lesson8 : BaseUiTest() {

    @Test
    @DisplayName("Проверка названия на главной странице")
    fun testHeaderTitle() {
        val title = MainPage()
            .navigateHeader()
            .getHeaderTitle()

        title shouldBe "Brew & Bean"
    }

    @Test
    @DisplayName("Проверка текста баннера на главной странице")
    fun testBannerTitle() {
        val title = MainPage()
            .getBannerTitle()

        title shouldBe "Welcome to Brew & Bean"
    }

    @Test
    @DisplayName("Проверка перехода на страницу Products")
    fun testNavigation() {
        MainPage()
            .navigateHeader()
            .clickLink("Products")
        val productsTitle = ProductsPage()
            .getTitle()

        productsTitle shouldBe "All Products"
    }

    @Test
    @DisplayName("Проверка количества товаров на странице Products")
    fun testProductsCards() {
        MainPage()
            .navigateHeader()
            .clickLink("Products")
        val products = ProductsPage()
            .getProducts()

        products shouldHaveSize 6
    }

    @Test
    @DisplayName("Проверка названия проекта на странице контактов")
    fun testNameProject() {
        MainPage()
            .navigateHeader()
            .clickLink("Contact")
        val nameProject = ContactPage()
            .getNameProject()

        nameProject shouldBe "Testing Playground Frontend"
    }
}