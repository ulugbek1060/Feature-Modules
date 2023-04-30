package uz.bigboys.sign_up.domain.entity

data class SignUpData(
    val email: String,
    val username: String,
    val password: String,
    val repeatPassword: String,
)