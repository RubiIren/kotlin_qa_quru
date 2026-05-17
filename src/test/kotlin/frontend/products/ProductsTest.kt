package frontend.products

import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.api.models.users.defaultUser
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import backend.helpers.ProductsHelper
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Feature("")
@Story("products")
class ProductsTest : BaseUiTest() {
    private val controllers = Controllers()
    val productsHelper = ProductsHelper()
    val authHelper = AuthorizationHelper()


    @Test
    @DisplayName("Check popular products exist")
    fun testPopularProductsExist() {
        // Precondition
        controllers.users.createUser(defaultUser).getAsObject()
        val token = authHelper.getToken(email = defaultUser.email, password = defaultUser.password)

        val body = CreateProductRequest(name = "Raf", description = "Raff coffee", price = 27.5)
        val product = controllers.products.createProduct(token = token, body).getAsObject()

        // Steps
        val popularList = MainPage()
            .open()
            .getPopularProducts()

        // Assertions
        popularList.size shouldBe 1
        popularList.first().name shouldBe product.name
    }

    @Test
    @DisplayName("Проверить создане 5ти продуктов")
    fun testFiveProductsExist() {
        controllers.users.createUser(defaultUser).getAsObject()
        val token = authHelper.getToken(email = defaultUser.email, password = defaultUser.password)

        val listOfProducts = productsHelper.createProductsWithToken(token, 5, "Coffee").sortedByDescending { it.name }

        val products = ProductsPage()
            .open()
            .getProductsAsObjects()
            .sortedByDescending { it.name }

        products.size shouldBe 10 // как 5 создается до теста
        products.forEachIndexed { index, product ->
            product.name.uppercase() shouldBe listOfProducts[index].name.uppercase()
        }
    }

    @Test
    @DisplayName("Проверить создание продуктов имеющих COFFEE в названии")
    fun testCoffeeProducts() {
        val user = controllers.users.createUser(defaultUser).getAsObject()
        val token = authHelper.getToken(email = user.email, password = defaultUser.password)
        val searchName = "COFFEE"

        val listOfProducts =
            productsHelper.createProductsWithToken(token, 5, searchName).sortedByDescending { it.name }
        println("Создано продуктов: ${listOfProducts.size}")

        val backendProducts = controllers.products.getProducts().getAsObject()
            .filter { it.name.contains(searchName, ignoreCase = true) }

        val products = ProductsPage()
            .open()
            .getProductsAsObjects()
            .filter { it.name.contains(searchName, ignoreCase = true) }

        products.size shouldBe backendProducts.size
        println("Найдено продуктов с '$searchName' в названии:")
        println("Бэкенд: ${backendProducts}")
        println("UI: ${products.size}")
    }
}

