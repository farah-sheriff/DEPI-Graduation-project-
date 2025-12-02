package Domain.Repository



import Domain.Model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun getSessionsByHabitId(habitId: Long): Flow<List<Session>>
    suspend fun getSessionsByHabitIdSync(habitId: Long): List<Session>
    fun getAllSessions(): Flow<List<Session>>
    suspend fun getAllSessionsSync(): List<Session>
    suspend fun addSession(session: Session): Long
    suspend fun deleteSession(session: Session)
    suspend fun deleteSessionsByHabitId(habitId: Long)
}

