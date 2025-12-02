package Data.Reprosoitry



import Data.database.HabitDatabase
import Data.mapper.toDomain
import Data.mapper.toEntity
import Domain.Model.Session
import Domain.Repository.SessionRepository
import android.content.Context

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionRepositoryImpl(context: Context) : SessionRepository {

    private val database = HabitDatabase.getDatabase(context)
    private val sessionDao = database.sessionDao()

    override fun getSessionsByHabitId(habitId: Long): Flow<List<Session>> {
        return sessionDao.getSessionsByHabitId(habitId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getSessionsByHabitIdSync(habitId: Long): List<Session> {
        return sessionDao.getSessionsByHabitIdSync(habitId).map { it.toDomain() }
    }

    override fun getAllSessions(): Flow<List<Session>> {
        return sessionDao.getAllSessions().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getAllSessionsSync(): List<Session> {
        return sessionDao.getAllSessionsSync().map { it.toDomain() }
    }

    override suspend fun addSession(session: Session): Long {
        return sessionDao.insertSession(session.toEntity())
    }

    override suspend fun deleteSession(session: Session) {
        sessionDao.deleteSession(session.toEntity())
    }

    override suspend fun deleteSessionsByHabitId(habitId: Long) {
        sessionDao.deleteSessionsByHabitId(habitId)
    }
}



