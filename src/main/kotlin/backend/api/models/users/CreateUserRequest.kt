package backend.api.models.users

data class CreateUserRequest(
    var username: String,
    var email: String,
    var password: String
)