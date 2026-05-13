package backend.api.models.users

data class UpdateResponse(
    var id: Int,
    var username: String,
    var email: String,
    var phoneNumber: String,
    var createdAt: Long
)