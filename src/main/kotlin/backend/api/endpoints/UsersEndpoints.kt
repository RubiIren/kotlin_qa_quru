package backend.api.endpoints

import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersEndpoints {
    @POST("users/create")
    fun postCreateUsers(@Body body: CreateUserRequest) : Call<CreateUserResponse>
}