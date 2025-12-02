package Data.mapper



import Data.Model.SessionEntity
import Domain.Model.Session


fun SessionEntity.toDomain(): Session {
    return Session(
        id = id,
        habitId = habitId,
        date = date,
        duration = duration
    )
}

fun Session.toEntity(): SessionEntity {
    return SessionEntity(
        id = id,
        habitId = habitId,
        date = date,
        duration = duration
    )
}



