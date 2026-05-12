package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import backend.api.models.users.UpdateRequest
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import io.qameta.allure.Step
import kotlinx.datetime.UtcOffset
import okhttp3.ResponseBody
import retrofit2.Response

class UsersController : Endpoints() {

    private val authHelper = AuthorizationHelper()

    @Step("Создание пользователя с username: {username} email: {email} and password: {password}")
    fun createUser(body: CreateUserRequest): Response<CreateUserResponse> {
        return users.createUser(body).execute()
            .also { GarbageCollector.user.add(it.getAsObject().id) }
    }

    @Step("Get user with id: {id}")
    fun getUserById(token: String = authHelper.getAdminToken(), id: Int): Response<CreateUserResponse> {
        return users.getUserById(token, id).execute()
    }

    @Step("Delete user with id: {id}")
    fun deleteUserById(token: String = authHelper.getAdminToken(), id: Int): Response<ResponseBody> {
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

    @Step("Получить всех пользователей")
    fun getAllUsers(token: String = authHelper.getAdminToken(), offset: Int = 0, limit: Int = 10): Response<List<CreateUserResponse>> {
        return users.getUsers(token, offset, limit).execute()
    }
}
