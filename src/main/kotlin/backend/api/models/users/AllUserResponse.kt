package backend.api.models.users

class AllUserResponse : ArrayList<AllUserResponseItem>()

data class AllUserResponseItem(
    var id: Int,
    var username: String,
    var email: String,
    var phoneNumber: String,
    var createdAt: Long
)