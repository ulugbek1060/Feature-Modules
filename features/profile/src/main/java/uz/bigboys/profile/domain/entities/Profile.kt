package uz.bigboys.profile.domain.entities

data class Profile(
    val id: Long,
    val username: String,
    val email: String,
    val createdAtString: String,
)