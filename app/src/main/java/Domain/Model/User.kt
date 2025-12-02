package Domain.Model

data class User(
    val id: Long = 1,
    val name: String,
    val surname: String,
    val birthdate: String
)

