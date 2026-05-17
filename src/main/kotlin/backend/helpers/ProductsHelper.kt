package backend.helpers

import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.CreateProductResponse
import backend.controllers.Controllers
import io.qameta.allure.Step

class ProductsHelper : Controllers() {

    @Step("Создание товаров в количестве: {count}")
    fun createProducts(count: Int): List<CreateProductResponse> {
        val listOfProducts = mutableListOf<CreateProductResponse>()

        repeat(count) { index ->
            listOfProducts.add(
                products.createProduct(
                    product = CreateProductRequest(
                        "Raf #$index",
                        description = "Description for product coffee #$index",
                        price = index + 1.toDouble()
                    )
                ).getAsObject()
            )
        }

        return listOfProducts.toList()
    }

    @Step("Создание товаров c токеномм в количестве: {count}")
    fun createProductsWithToken(token: String, count: Int, name: String): List<CreateProductResponse> {
        val listOfProducts = mutableListOf<CreateProductResponse>()

        repeat(count) { index ->
            listOfProducts.add(
                products.createProduct(
                    product = CreateProductRequest(
                        "$name #$index",
                        description = "Description for product coffee #$index",
                        price = index + 1.toDouble()
                    )
                ).getAsObject()
            )
        }

        return listOfProducts.toList()
    }
}

