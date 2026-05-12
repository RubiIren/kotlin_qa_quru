package backend.helpers

import backend.api.extension.Extensions.Companion.getAsObject
import backend.api.extension.Extensions.Companion.toBearer
import backend.api.models.auth.defaultAdmin
import backend.controllers.Controllers
import io.qameta.allure.Step

class AuthorizationHelper: Controllers() {

    @Step("Get authorization token")
    fun getToken(email: String, password: String): String {
        return auth.login(email, password).getAsObject().accessToken.toBearer()
    }

    @Step("Get ADMIN token")
    fun getAdminToken(): String {
        return auth.login(email = defaultAdmin.email, password = defaultAdmin.password).getAsObject().accessToken.toBearer()
    }
}