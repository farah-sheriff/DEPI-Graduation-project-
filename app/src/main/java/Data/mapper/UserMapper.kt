package Data.mapper




import Data.Model.UserEntity
import Domain.Model.User


fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        surname = surname,
        birthdate = birthdate
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        surname = surname,
        birthdate = birthdate
    )
}

