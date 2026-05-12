package backend.api.endpoints

import backend.api.RetrofitClient

open class Endpoints {
    protected val auth: AuthEndpoints by lazy { RetrofitClient.createService(AuthEndpoints::class.java) }
    protected val users: UsersEndpoints by lazy { RetrofitClient.createService(UsersEndpoints::class.java) }
}