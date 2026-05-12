package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.UpdateRequest
import backend.helpers.AuthorizationHelper
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class UsersController : Endpoints() {

    private val authHelper = AuthorizationHelper()

    @Step("Создание пользователя с username: {username} email: {email} and password: {password}")
    fun createUser(body: CreateUserRequest): Response<CreateUserResponse> {
        return users.createUser(body).execute()
    }

    @Step("Get user with id: {id}")
    fun getUserById(token: String, id: Int): Response<CreateUserResponse> {
        return users.getUserById(token, id).execute()
    }

    @Step("Delete user with id: {id}")
    fun deleteUserById(token: String, id: Int): Response<ResponseBody> {
        return users.deleteUserById(token, id).execute()
    }

    @Step("Обновление данных пользователя: {id}")
    fun updateUserById(
        token: String = authHelper.getAdminToken(),
        id: Int,
        body: UpdateRequest
    ): Response<CreateUserResponse> {
        return users.putUserById(token, id, body).execute()
    }
}
