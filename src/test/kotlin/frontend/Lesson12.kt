package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import general.Config.get
import general.JsonConfig
import general.TestsWatcher
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestsWatcher::class)
class Lesson12 : BaseUiTest() {

    @Test
    @DisplayName("Проверка падения теста")
    fun testNavigationError() {
        MainPage()
            .navigateHeader()
            .clickLink("Product")
        val productsTitle = ProductsPage()
            .getTitle()

        productsTitle shouldBe "All Products"
    }

    @Test
    @DisplayName("Проверка успешного теста")
    fun testNavigation() {
        MainPage()
            .navigateHeader()
            .clickLink("Products")
        val productsTitle = ProductsPage()
            .getTitle()

        productsTitle shouldBe "All Products"
    }

    @Test
    @DisplayName("Проверка отключенного теста")
    @Disabled("отключено")
    fun testNavigationDisabled() {
        MainPage()
            .navigateHeader()
            .clickLink("Products")
        val productsTitle = ProductsPage()
            .getTitle()

        productsTitle shouldBe "All Products"
    }

    @Test
    @DisplayName("Проверка загрузки из файла properties ")
    fun testPropertyKoLoading() {
        println("Property file: $get")
    }


    @Test
    @DisplayName("Проверка загрузки из файла json")
    fun testPropertyJsonLoading() {
        val jsonProps = JsonConfig.get
        println("Property file: $jsonProps")
        println("Browser: ${jsonProps.browserName}")
    }
}