package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import io.qameta.allure.Step
import retrofit2.Response

class UsersController : Endpoints() {

    @Step("Создание пользователя с username: {username} email: {email} and password: {password}")
    fun create(username: String, email: String, password: String): Response<CreateUserResponse> {
        return users.postCreateUsers(body = CreateUserRequest(username = username, email = email, password = password))
            .execute()
    }
}