package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.CreateProductResponse
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class ProductsController: Endpoints() {
    val authHelper = AuthorizationHelper()

    @Step("Get all products")
    fun getProducts(): Response<List<CreateProductResponse>> {
        return products.getProducts().execute()
    }

    @Step("Get product with id: {id}")
    fun getProductById(id: Any): Response<CreateProductResponse> {
        return products.getProductById(id).execute()
    }

    @Step("Create a new product")
    fun createProduct(token: String = authHelper.getAdminToken(), product: CreateProductRequest): Response<CreateProductResponse> {
        return products.postCreateProduct(token, product).execute()
            .also { GarbageCollector.products.add(it.getAsObject().id) }
    }

    @Step("Delete product with id: {id}")
    fun deleteProductById(token: String = authHelper.getAdminToken(), id: Any): Response<ResponseBody> {
        return products.deleteProductById(token, id).execute()
    }
}